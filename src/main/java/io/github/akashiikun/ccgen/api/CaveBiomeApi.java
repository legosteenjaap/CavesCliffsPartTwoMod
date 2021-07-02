package io.github.akashiikun.ccgen.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.MixedNoisePoint;

public class CaveBiomeApi {
	
	private static ArrayList<Pair<Biome.MixedNoisePoint,RegistryKey<Biome>>> biomes = new ArrayList();	
	
	public static void registerCaveBiome(RegistryKey<Biome> biome, float temperature, float humidity, float altitude, float weirdness, float weight) {
		biomes.add(Pair.of(new Biome.MixedNoisePoint(temperature, humidity, altitude, weirdness, weight), biome));
	}
	
	public static ArrayList<Pair<Biome.MixedNoisePoint,RegistryKey<Biome>>> getCaveBiomes () {
		return biomes;
	}
	
}
