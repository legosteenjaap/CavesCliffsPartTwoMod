package io.github.akashiikun.ccgen;

import net.fabricmc.api.ModInitializer;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class CavesCliffsGenerationMod implements ModInitializer{

	public static final String modid = "ccgen";
	
	@Override
	public void onInitialize() {
		WorldGen.init();
	}

}
