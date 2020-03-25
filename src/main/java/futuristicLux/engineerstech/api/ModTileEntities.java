package futuristicLux.engineerstech.api;

import futuristicLux.engineerstech.common.tileEntities.CableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("engineerstech")
public class ModTileEntities {
	
	@ObjectHolder("engineerstech:cable")
	public static TileEntityType<CableTileEntity> CABLE;

}
