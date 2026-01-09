package com.tomaszenko7.easyender;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block DEEPSLATE_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "deepslate_ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Item ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "ender_ore"),
            new EnderOreBlockItem(ENDER_ORE, new Item.Settings())
    );

    public static final Item DEEPSLATE_ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "deepslate_ender_ore"),
            new EnderOreBlockItem(DEEPSLATE_ENDER_ORE, new Item.Settings())
    );

    public static final Block END_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Item END_ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new EnderOreBlockItem(END_ENDER_ORE, new Item.Settings())
    );

    public static void register() {

    }
}