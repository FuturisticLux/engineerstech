package futuristicLux.engineerstech;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import futuristicLux.engineerstech.api.ModBlocks;
import futuristicLux.engineerstech.world.gen.OreGeneration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("engineerstech")
public class EngineersTech {
	// Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    

    /**
     * mod constructor
     */
    public EngineersTech() {
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    //setup event
    private void setup(final FMLCommonSetupEvent event)
    {    	
        // some preinit code
        //LOGGER.info("HELLO FROM PREINIT");
        //LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    	
    	OreGeneration.oreGeneration();
        
        
    }

	//client stuff gets done
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    //send to another mod
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    //receive from another mod
    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
          //      map(m->m.getMessageSupplier().get()).
            //    collect(Collectors.toList()));
    }
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }
    
 // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        
        
        @SubscribeEvent
        public static void registerTE(@Nonnull final RegistryEvent.Register<TileEntityType<?>> evt) {
        	//create(TileEntity class, valid blocks for TileEntity
        	
        	LOGGER.info("Tile Entitys Registered");
        }
		
    }
    
    private void oreGeneration() {
    	
		for(Biome biome : ForgeRegistries.BIOMES) {
			
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CHALCOPYRITE_ORE.getDefaultState(), 12)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(30.0f, 0, 0, 127))));
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CASSITERITE_ORE.getDefaultState(), 5)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(12.0f, 0, 0, 127))));

			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CASSITERITE_ORE.getDefaultState(), 9)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(22.0f, 0, 0, 45))));

	    	biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
	                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.MALACHITE_ORE.getDefaultState(), 8)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(16.0f, 0, 0, 22))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.GALENA_ORE.getDefaultState(), 9)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(20.0f, 45, 0, 127))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.RUTILE_ORE.getDefaultState(), 7)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(14.0f, 0, 0, 15))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.BAUXITE_ORE.getDefaultState(), 12)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(20.0f, 60, 0, 127))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.URANINITE_ORE.getDefaultState(), 3)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(12.0f, 0, 0, 45))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.FLUORITE_ORE.getDefaultState(), 5)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(15.0f, 0, 0, 45))));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.MONAZITE_ORE.getDefaultState(), 2)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(new ChanceRangeConfig(10.0f, 0, 0, 30))));
	    	
					
		}
		
	}
    
    

}

