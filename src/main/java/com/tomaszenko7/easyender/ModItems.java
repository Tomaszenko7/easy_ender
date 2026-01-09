package com.tomaszenko7.easyender;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ENDER_FRAGMENT = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "ender_fragment"),
            new EnderFragmentItem(new Item.Settings().food(
                    new net.minecraft.item.FoodComponent.Builder()
                            .hunger(1)
                            .saturationModifier(0.1f)
                            .alwaysEdible()
                            .build()
            ))
    );

    public static final Item ENDER_DUST = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "ender_dust"),
            new Item(new Item.Settings())
    );

    public static void register() {
        // Adds to creative tab for easy testing
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ENDER_FRAGMENT);
            entries.add(ENDER_DUST);
        });
    }
}