package futuristicLux.engineerstech.common.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EngineersTechTab extends ItemGroup{
	
	public static final EngineersTechTab instance = new EngineersTechTab(ItemGroup.GROUPS.length, "engineerstech");

	public EngineersTechTab(int index, String label) {
		super(index,label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Blocks.REDSTONE_BLOCK);
	}

}
