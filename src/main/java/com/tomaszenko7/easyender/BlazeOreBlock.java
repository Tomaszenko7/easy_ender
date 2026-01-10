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

public class BlazeOreBlock extends ExperienceDroppingBlock {

    public BlazeOreBlock(Settings settings, UniformIntProvider xp) {
        super(settings, xp);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.isClient) return;

        boolean playerNearby = !world.getEntitiesByClass(
                PlayerEntity.class,
                new Box(pos).expand(8),
                p -> true
        ).isEmpty();

        if (!playerNearby) return;

        if (random.nextInt(250) == 0) {
            world.playSound(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    SoundEvents.BLOCK_FIRE_AMBIENT,
                    SoundCategory.BLOCKS,
                    0.15f,
                    1.1f,
                    false
            );
        }

        if (random.nextInt(2) == 0) {
            world.addParticle(
                    ParticleTypes.FLAME,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 1.02,
                    pos.getZ() + random.nextDouble(),
                    0.0, 0.01, 0.0
            );
        }

        if (random.nextInt(6) == 0) {
            world.addParticle(
                    ParticleTypes.SMOKE,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 1.02,
                    pos.getZ() + random.nextDouble(),
                    0.0, 0.02, 0.0
            );
        }
    }
}