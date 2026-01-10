package com.tomaszenko7.easyender;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //Ender items

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
            new EnderDustItem(new Item.Settings())
    );

    public static final Item ENDER_SLURRY = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "ender_slurry"),
            new EnderSlurryItem(new Item.Settings())
    );

    //Blaze items

    public static final Item BLAZE_FRAGMENT = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "blaze_fragment"),
            new BlazeFragmentItem(new Item.Settings())
    );

    public static final Item BLAZE_DUST = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "blaze_dust"),
            new BlazeDustItem(new Item.Settings())
    );

    public static final Item BLAZE_SLURRY = Registry.register(
            Registries.ITEM,
            new Identifier(EasyEnder.MOD_ID, "blaze_slurry"),
            new BlazeSlurryItem(new Item.Settings())
    );

    public static void register() {
    }
}