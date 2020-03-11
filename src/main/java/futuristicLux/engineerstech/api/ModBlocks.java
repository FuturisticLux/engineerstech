package futuristicLux.engineerstech.api;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("engineerstech")
public class ModBlocks {
	
	//pickaxe stone, drops ore block, coal deep, melts for copper, copper and iron ore
	@ObjectHolder("engineerstech:chalcopyrite_ore")
	public static Block CHALCOPYRITE_ORE;
	
	//Pickaxe: stone, drops Malachite "gem", gold deep, melts for copper, copper and cobal ore
	@ObjectHolder("engineerstech:malachite_ore")
	public static Block MALACHITE_ORE;
	
	//Pickaxe: stone, drops ore block, coal deep rare gold deep common, melts for tin, tin ore, iron impurities
	@ObjectHolder("engineerstech:cassiterite_ore")
	public static Block CASSITERITE_ORE;
	
	//Pickaxe: iron, drops ore block, surface deep, doen't melt, aluminum ore, iron and titanium impurities
	@ObjectHolder("engineerstech:bauxite_ore")
	public static Block BAUXITE_ORE;
	
	//Pickaxe: iron, drops Fluorite "gem", gold deep very rare, doen't melt, fluor ore
	@ObjectHolder("engineerstech:fluorite_ore")
	public static Block FLUORITE_ORE;
		
	//Pickaxe: stone, drops ore block, iron deep, melts for lead, lead and silver(impurity) ore
	@ObjectHolder("engineerstech:galena_ore")
	public static Block GALENA_ORE;
	
	//Pickaxe: iron, drops ore block, diamond deep rare, doen't melt, rare earths and thorium ore, uranium impurities
	@ObjectHolder("engineerstech:monazite_ore")
	public static Block MONAZITE_ORE;
	
	//Pickaxe: iron, drops ore block, diamond deep normal, melts for titanium, titanium ore niobium impurities
	@ObjectHolder("engineerstech:rutile_ore")
	public static Block RUTILE_ORE;
	
	//Pickaxe: iron, drops ore block, gold deep very rare, doen't melt, uranium ore, lead impurities
	@ObjectHolder("engineerstech:uraninite_ore")
	public static Block URANINITE_ORE;
	
	@ObjectHolder("engineerstech:cable")
	public static Block CABLE;
}
