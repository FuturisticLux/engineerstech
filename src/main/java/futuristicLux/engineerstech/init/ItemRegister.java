package futuristicLux.engineerstech.init;

import futuristicLux.engineerstech.common.util.EngineersTechTab;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
		
		registerItem(new Item(new Item.Properties().group(EngineersTechTab.instance)), "malachite");
		registerItem(new Item(new Item.Properties().group(EngineersTechTab.instance)), "fluorite");
		
	}

    public static Item registerItem(Item item, String name){
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
