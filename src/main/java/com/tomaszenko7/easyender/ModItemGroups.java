package com.tomaszenko7.easyender;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup EASY_ENDER_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(EasyEnder.MOD_ID, "easy_ender"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.easy_ender"))
                    .icon(() -> new ItemStack(ModItems.ENDER_FRAGMENT))
                    .entries((context, entries) -> {
                        entries.add(ModItems.ENDER_FRAGMENT);
                        entries.add(ModItems.ENDER_DUST);
                        entries.add(ModBlocks.ENDER_ORE_ITEM);
                        entries.add(ModBlocks.DEEPSLATE_ENDER_ORE_ITEM);
                        entries.add(ModBlocks.END_ENDER_ORE_ITEM);
                    })
                    .build()
    );

    public static void register() {
    }
}