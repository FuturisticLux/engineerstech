package futuristicLux.engineerstech.init;

import futuristicLux.engineerstech.common.block.*;
import futuristicLux.engineerstech.common.util.EngineersTechTab;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {
	
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound( SoundType.STONE )), "chalcopyrite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound( SoundType.STONE )), "malachite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound( SoundType.STONE )), "cassiterite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound( SoundType.STONE )), "galena_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound( SoundType.STONE )), "bauxite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound( SoundType.STONE )), "rutile_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound( SoundType.STONE )), "monazite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound( SoundType.STONE )), "uraninite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.ROCK ).hardnessAndResistance(3, 3).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound( SoundType.STONE )), "fluorite_ore");
		registerBlock(new OreBlock(Block.Properties.create( Material.GLASS ).hardnessAndResistance(3, 3).sound( SoundType.GLASS ).variableOpacity()), "cable");
    }
	
	public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(EngineersTechTab.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlockNoGroup(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(null));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
    
    public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }
}
