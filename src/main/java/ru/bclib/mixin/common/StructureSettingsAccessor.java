package ru.bclib.mixin.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StructureSettings.class)
public interface StructureSettingsAccessor {
	@Accessor("configuredStructures")
	ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> bcl_getConfiguredStructures();
	
	@Accessor("structureConfig")
	Map<StructureFeature<?>, StructureFeatureConfiguration> bcl_getStructureConfig();
	
	@Accessor("configuredStructures")
	@Mutable
	void bcl_setConfiguredStructures(ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> configuredStructures);
	
	@Accessor("structureConfig")
	@Mutable
	void bcl_setStructureConfig(Map<StructureFeature<?>, StructureFeatureConfiguration> structureConfig);
}
