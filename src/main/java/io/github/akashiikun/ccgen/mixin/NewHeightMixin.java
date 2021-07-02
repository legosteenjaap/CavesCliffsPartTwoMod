package io.github.akashiikun.ccgen.mixin;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalLong;

@Mixin(DimensionType.class)
public class NewHeightMixin {

	/**
	 * @author Legosteenjaap
	 */
	
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
	private static void create(OptionalLong fixedTime, boolean hasSkylight, boolean hasCeiling, boolean ultrawarm,
			boolean natural, double coordinateScale, boolean hasEnderDragonFight, boolean piglinSafe, boolean bedWorks,
			boolean respawnAnchorWorks, boolean hasRaids, int minimumY, int height, int logicalHeight,
			BiomeAccessType biomeAccessType, Identifier infiniburn, Identifier dim, float ambientLight, CallbackInfoReturnable<DimensionType> cir) {
		
		
		
    	
		if (dim.toString().equals("minecraft:overworld")) {
			if (minimumY != ConfigValues.minYOverworld || height != ConfigValues.heightOverworld
					|| logicalHeight != ConfigValues.logicalHeightOverworld) {
				cir.setReturnValue(DimensionType.create(fixedTime, hasSkylight, hasCeiling, ultrawarm, natural,
						coordinateScale, hasEnderDragonFight, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, ConfigValues.minYOverworld,
						ConfigValues.heightOverworld, ConfigValues.logicalHeightOverworld, biomeAccessType, infiniburn, dim, ambientLight));
			}
		}
		
		if (dim.toString().equals("minecraft:the_nether")) {
			if (minimumY != ConfigValues.minYNether || height != ConfigValues.heightNether
					|| logicalHeight != ConfigValues.logicalHeightNether) {
				cir.setReturnValue(DimensionType.create(fixedTime, hasSkylight, hasCeiling, ultrawarm, natural,
						coordinateScale, hasEnderDragonFight, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, ConfigValues.minYNether,
						ConfigValues.heightNether, ConfigValues.logicalHeightNether, biomeAccessType, infiniburn, dim, ambientLight));
			}
		}
		
		if (dim.toString().equals("minecraft:the_end")) {
			if (minimumY != ConfigValues.minYEnd || height != ConfigValues.heightEnd
					|| logicalHeight != ConfigValues.logicalHeightEnd) {
				cir.setReturnValue(DimensionType.create(fixedTime, hasSkylight, hasCeiling, ultrawarm, natural,
						coordinateScale, hasEnderDragonFight, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, ConfigValues.minYEnd,
						ConfigValues.heightEnd, ConfigValues.logicalHeightEnd, biomeAccessType, infiniburn, dim, ambientLight));
			}
		}

	}

}
