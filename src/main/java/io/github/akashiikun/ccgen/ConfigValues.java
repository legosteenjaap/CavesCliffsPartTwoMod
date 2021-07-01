package io.github.akashiikun.ccgen;

import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.gen.SimpleRandom;

public class ConfigValues {

    public static final int minYOverworld = -64;
    public static final int heightOverworld = 384;
    public static final int logicalHeightOverworld = 384;
    public static final int minSurfaceLevel = 50;
    public static final int seaLevel = 63;
    public static final int deepslateLevel = 0;


    public static final int minYNether = -64;
    public static final int generationHeightNether = 384;
    public static final int heightNether = 384;
    public static final int logicalHeightNether = 384;
    public static final int lavaSeaLevel = -32;
    
    public static final int minYEnd = -64;
    public static final int generationHeightEnd = 256;
    public static final int heightEnd = 256;
    public static final int logicalHeightEnd= 256;

    public static final boolean deepslate = true;
    public static final boolean usePrototypeGeneration = true;
    public static final boolean noiseCaves = true;
    public static final boolean noodleCaves = true;
    public static final boolean aquifers = true;
}
