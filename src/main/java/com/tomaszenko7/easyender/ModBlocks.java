package com.tomaszenko7.easyender;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "ender_ore"),
            new ExperienceDroppingBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Block DEEPSLATE_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "deepslate_ender_ore"),
            new ExperienceDroppingBlock(
                    Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Item ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "ender_ore"),
            new BlockItem(ENDER_ORE, new Item.Settings())
    );

    public static final Item DEEPSLATE_ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "deepslate_ender_ore"),
            new BlockItem(DEEPSLATE_ENDER_ORE, new Item.Settings())
    );

    public static final Block END_ENDER_ORE = Registry.register(
            Registries.BLOCK,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new ExperienceDroppingBlock(
                    Block.Settings.copy(Blocks.DIAMOND_ORE).luminance(state -> 6),
                    UniformIntProvider.create(2, 5)
            )
    );

    public static final Item END_ENDER_ORE_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "end_ender_ore"),
            new BlockItem(END_ENDER_ORE, new Item.Settings())
    );

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ENDER_ORE_ITEM);
            entries.add(DEEPSLATE_ENDER_ORE_ITEM);
            entries.add(END_ENDER_ORE_ITEM);
        });
    }
}