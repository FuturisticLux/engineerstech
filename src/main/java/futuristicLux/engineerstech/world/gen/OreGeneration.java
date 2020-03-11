package futuristicLux.engineerstech.world.gen;

import futuristicLux.engineerstech.api.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

	public static void oreGeneration() {
	
		for(Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CHALCOPYRITE_ORE.getDefaultState(), 11)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(25, 0, 0, 127))));
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CASSITERITE_ORE.getDefaultState(), 5)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(7, 0, 0, 127))));

			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CASSITERITE_ORE.getDefaultState(), 9)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 45))));

	    	biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
	                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.MALACHITE_ORE.getDefaultState(), 8)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(7, 0, 0, 22))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.GALENA_ORE.getDefaultState(), 9)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 45, 0, 127))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.RUTILE_ORE.getDefaultState(), 7)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 15))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.BAUXITE_ORE.getDefaultState(), 12)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 60, 0, 127))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.URANINITE_ORE.getDefaultState(), 3)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(15, 0, 0, 45))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.FLUORITE_ORE.getDefaultState(), 5)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(7, 0, 0, 50))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.MONAZITE_ORE.getDefaultState(), 3)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(11, 0, 0, 35))));
	    	}
	}
}
