package futuristicLux.engineerstech.init;

import javax.annotation.Nonnull;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

	@SubscribeEvent
    public static void registerTE(@Nonnull final RegistryEvent.Register<TileEntityType<?>> evt) {
    	
    	
    }
}
