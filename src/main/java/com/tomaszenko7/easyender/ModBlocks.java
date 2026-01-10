package com.tomaszenko7.easyender;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    //Ender ore blocks

    public static final Block ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block DEEPSLATE_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "deepslate_ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block END_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new EnderOreBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE),
                    UniformIntProvider.create(2, 5)
            )
    );

    //Ender ore items

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

    public static final Item END_ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new EnderOreBlockItem(END_ENDER_ORE, new Item.Settings())
    );

    //Blaze ore blocks

    public static final Block BLAZE_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "blaze_ore"),
            new BlazeOreBlock(
                    Block.Settings.copy(Blocks.GOLD_ORE).luminance(state -> 8),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block DEEPSLATE_BLAZE_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "deepslate_blaze_ore"),
            new BlazeOreBlock(
                    Block.Settings.copy(Blocks.DEEPSLATE_GOLD_ORE).luminance(state -> 8),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block NETHER_BLAZE_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "nether_blaze_ore"),
            new BlazeOreBlock(
                    Block.Settings.copy(Blocks.NETHER_GOLD_ORE).luminance(state -> 8),
                    UniformIntProvider.create(2, 5)
            )
    );

    //Blaze ore items

    public static final Item BLAZE_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "blaze_ore"),
            new BlazeOreBlockItem(BLAZE_ORE, new Item.Settings())
    );

    public static final Item DEEPSLATE_BLAZE_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "deepslate_blaze_ore"),
            new BlazeOreBlockItem(DEEPSLATE_BLAZE_ORE, new Item.Settings())
    );

    public static final Item NETHER_BLAZE_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "nether_blaze_ore"),
            new BlazeOreBlockItem(NETHER_BLAZE_ORE, new Item.Settings())
    );

    public static void register() {

    }
}