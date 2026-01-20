package com.tomaszenko7.easyender;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class EnderFragmentItem extends Item {

    private static final int TELEPORT_RADIUS = 1000;
    private static final int MAX_ATTEMPTS = 256;          // high = very reliable
    private static final int COOLDOWN_TICKS = 20 * 10;      // 5 seconds
    private static final int NAUSEA_TICKS = 20 * 10;       // 10 seconds

    public EnderFragmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // If we're on cooldown, don't even start eating
        if (!world.isClient && user instanceof ServerPlayerEntity sp) {
            if (sp.getItemCooldownManager().isCoolingDown(this)) {
                return TypedActionResult.fail(stack);
            }
        }

        if (user.canConsume(true)) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }
        return TypedActionResult.fail(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.easy_ender.ender_fragment.tooltip").formatted(Formatting.DARK_PURPLE));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            ServerWorld serverWorld = (ServerWorld) world;

            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return result;
            }

            TeleportTarget target = findRandomSafeTarget(player, serverWorld);

            if (target != null) {
                double startX = player.getX();
                double startY = player.getY();
                double startZ = player.getZ();

                player.requestTeleport(target.x, target.y, target.z);
                float damageAmount = 4.0F;
                float maxAllowed = player.getHealth() - 1.0F;
                if (maxAllowed > 0.0F) {
                    player.damage(player.getDamageSources().magic(), Math.min(damageAmount, maxAllowed));
                }

                playTeleportEffects(serverWorld, startX, startY, startZ);
                playTeleportEffects(serverWorld, target.x, target.y, target.z);

                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, NAUSEA_TICKS, 0));
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }
        }
        return result;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    private static void playTeleportEffects(ServerWorld world, double x, double y, double z) {
        world.playSound(
                null,
                x, y, z,
                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        world.spawnParticles(
                ParticleTypes.PORTAL,
                x, y + 1.0, z,
                40,
                0.5, 0.8, 0.5,
                0.1
        );
    }

    private static TeleportTarget findRandomSafeTarget(ServerPlayerEntity player, ServerWorld world) {
        double startX = player.getX();
        double startZ = player.getZ();
        int startY = player.getBlockY();

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        boolean hasCeiling = world.getDimension().hasCeiling();

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            int dx = world.getRandom().nextBetween(-TELEPORT_RADIUS, TELEPORT_RADIUS);
            int dz = world.getRandom().nextBetween(-TELEPORT_RADIUS, TELEPORT_RADIUS);
            int targetX = MathHelper.floor(startX) + dx;
            int targetZ = MathHelper.floor(startZ) + dz;
            mutable.set(targetX, 64, targetZ);
            if (!world.getWorldBorder().contains(mutable)) continue;
            int chunkX = targetX >> 4;
            int chunkZ = targetZ >> 4;
            if (world.getChunk(chunkX, chunkZ, net.minecraft.world.chunk.ChunkStatus.FULL, false) == null) continue;
            int y;
            if (hasCeiling) {
                int maxY = Math.min(world.getTopY() - 2, 120); // stay below bedrock roof zone
                int minY = world.getBottomY() + 2;
                y = MathHelper.clamp(startY + world.getRandom().nextBetween(-32, 32), minY, maxY);
                boolean found = false;
                for (int scan = 0; scan < 48 && y > minY; scan++, y--) {
                    if (isSafeSpot(world, mutable, targetX, y, targetZ)) {
                        found = true;
                        break;
                    }
                }
                if (!found) continue;

            } else {
                y = world.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, targetX, targetZ);
                if (!isSafeSpot(world, mutable, targetX, y, targetZ)) continue;
            }

            return new TeleportTarget(targetX + 0.5, y, targetZ + 0.5);
        }
        return null;
    }

    private static boolean isSafeSpot(ServerWorld world, BlockPos.Mutable mutable, int x, int y, int z) {
        mutable.set(x, y, z);
        if (!world.getBlockState(mutable).isAir()) return false;
        mutable.set(x, y + 1, z);
        if (!world.getBlockState(mutable).isAir()) return false;
        mutable.set(x, y - 1, z);
        var stateBelow = world.getBlockState(mutable);
        if (!stateBelow.isSolidBlock(world, mutable)) return false;
        if (!world.getFluidState(mutable).isEmpty()) return false;
        return !stateBelow.isOf(net.minecraft.block.Blocks.BEDROCK);
    }

    private record TeleportTarget(double x, double y, double z) {}
}