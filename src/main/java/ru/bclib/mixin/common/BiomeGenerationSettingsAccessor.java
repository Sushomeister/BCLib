package ru.bclib.mixin.common;

import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(BiomeGenerationSettings.class)
public interface BiomeGenerationSettingsAccessor {
	@Accessor("features")
	List<List<Supplier<PlacedFeature>>> bclib_getFeatures();
	
	@Accessor("features")
	@Mutable
	void bclib_setFeatures(List<List<Supplier<PlacedFeature>>> value);
	
	@Accessor("featureSet")
	Set<PlacedFeature> bclib_getFeatureSet();
	
	@Accessor("featureSet")
	void bclib_setFeatureSet(Set<PlacedFeature> features);
	
	@Accessor("carvers")
	Map<Carving, List<Supplier<ConfiguredWorldCarver<?>>>> bclib_getCarvers();
	
	@Accessor("carvers")
	void bclib_setCarvers(Map<GenerationStep.Carving, List<Supplier<ConfiguredWorldCarver<?>>>> features);
}
