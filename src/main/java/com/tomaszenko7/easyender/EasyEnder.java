package com.tomaszenko7.easyender;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyEnder implements ModInitializer {
	public static final String MOD_ID = "easy_ender";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModWorldGen.register();
		ModItems.register();
		ModBlocks.register();
		ModItemGroups.register();
	}
}