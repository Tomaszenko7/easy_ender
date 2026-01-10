package com.tomaszenko7.easyender;

import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EnderOreBlock extends ExperienceDroppingBlock {

    public EnderOreBlock(Settings settings, UniformIntProvider xp) {
        super(settings, xp);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.isClient) return;

        boolean playerNearby = !world.getEntitiesByClass(
                PlayerEntity.class,
                new Box(pos).expand(8),
                player -> true
        ).isEmpty();

        if (!playerNearby) return;

        if (random.nextInt(160) == 0) {
            world.playSound(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                    SoundCategory.BLOCKS,
                    0.35f,   // volume (quiet)
                    1.2f,    // pitch
                    false
            );
        }

        for (int i = 0; i < 2; i++) {
            world.addParticle(
                    ParticleTypes.PORTAL,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(),
                    0.0, 0.02, 0.0
            );
        }
    }
}