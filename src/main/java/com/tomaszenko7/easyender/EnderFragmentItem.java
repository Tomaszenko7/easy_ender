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
import net.minecraft.world.Heightmap;
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

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            int dx = world.getRandom().nextBetween(-TELEPORT_RADIUS, TELEPORT_RADIUS);
            int dz = world.getRandom().nextBetween(-TELEPORT_RADIUS, TELEPORT_RADIUS);

            int targetX = MathHelper.floor(startX) + dx;
            int targetZ = MathHelper.floor(startZ) + dz;

            if (!world.getWorldBorder().contains(new BlockPos(targetX, 64, targetZ))) continue;

            int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, targetX, targetZ);
            BlockPos feet = new BlockPos(targetX, topY, targetZ);

            if (!world.getBlockState(feet).isAir()) continue;
            if (!world.getBlockState(feet.up()).isAir()) continue;

            BlockPos below = feet.down();
            if (!world.getBlockState(below).isSolidBlock(world, below)) continue;
            if (!world.getFluidState(below).isEmpty()) continue;

            return new TeleportTarget(targetX + 0.5, topY, targetZ + 0.5);
        }

        return null;
    }

    private record TeleportTarget(double x, double y, double z) {}
}