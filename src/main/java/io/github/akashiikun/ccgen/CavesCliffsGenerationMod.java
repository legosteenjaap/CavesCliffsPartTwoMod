package io.github.akashiikun.ccgen;

import io.github.akashiikun.ccgen.core.api.CaveGenerationAPI;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;

public class CavesCliffsGenerationMod implements ModInitializer {
    public static final String MOD_ID = "ccgen";

    @Override
    public void onInitialize() {
        Reflection.initialize(CaveGenerationAPI.class);
    }
}
