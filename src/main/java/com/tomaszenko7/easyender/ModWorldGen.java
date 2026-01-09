package com.tomaszenko7.easyender;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGen {

    public static final RegistryKey<PlacedFeature> ENDER_ORE_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(EasyEnder.MOD_ID, "ender_ore_placed"));

    public static final RegistryKey<PlacedFeature> ENDER_ORE_END_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(EasyEnder.MOD_ID, "ender_ore_end_placed"));

    public static void register() {

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ENDER_ORE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ENDER_ORE_END_PLACED_KEY
        );
    }
}