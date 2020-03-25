package futuristicLux.engineerstech.init;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import futuristicLux.engineerstech.api.ModBlocks;
import futuristicLux.engineerstech.common.tileEntities.CableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

	@SubscribeEvent
    public static void registerTE(@Nonnull final RegistryEvent.Register<TileEntityType<?>> evt) {
    	
		register("cable", CableTileEntity::new, ModBlocks.CABLE);
    	
    }
	
	private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> tileFactory, Block... blocks) {
        TileEntityType<T> type = TileEntityType.Builder.create(tileFactory, blocks).build(null);
        return register(name, type);
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType<T> type) {
        if (type.getRegistryName() == null) {
            type.setRegistryName(name);
        }
        ForgeRegistries.TILE_ENTITIES.register(type);
        return type;
    }
}
