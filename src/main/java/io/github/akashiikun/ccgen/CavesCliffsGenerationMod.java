package io.github.akashiikun.ccgen;

import net.fabricmc.api.ModInitializer;


public class CavesCliffsGenerationMod implements ModInitializer {

	public static final String modid = "ccgen";
	
	@Override
	public void onInitialize() {
		WorldGen.init();
	}
}
