package com.tomaszenko7.easyender;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EnderDustItem extends Item {

    public EnderDustItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack,
                              World world,
                              List<Text> tooltip,
                              TooltipContext context) {
        tooltip.add(Text.translatable("item.easy_ender.ender_dust.tooltip")
                .formatted(Formatting.DARK_PURPLE));
    }
}