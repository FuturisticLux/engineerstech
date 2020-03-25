package futuristicLux.engineerstech.common.tileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import futuristicLux.engineerstech.api.ModTileEntities;
import futuristicLux.engineerstech.common.network.CableNetwork;
import futuristicLux.engineerstech.common.network.CableNetworkManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class CableTileEntity extends TileEntity {

	public int energyStored;
	
	public CableTileEntity() {
		super(ModTileEntities.CABLE);
	}
	
	public String getWireNetworkData() {
        if (world == null) return "world is null";

        CableNetwork net = CableNetworkManager.get(world, pos);
        return net != null ? net.toString() : "null";
    }
	
	@Override
    public void read(CompoundNBT compound) {
        this.energyStored = compound.getInt("EnergyStored");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("EnergyStored", energyStored);
        return super.write(compound);
    }
    
    @Override
    public void remove() {
        if (world != null) {
            CableNetworkManager.invalidateNetwork(world, pos);
        }
        super.remove();
    }
    
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (world != null && !removed && cap == CapabilityEnergy.ENERGY && side != null) {
            LazyOptional<CableNetwork> networkOptional = CableNetworkManager.getLazy(world, pos);
            if (networkOptional.isPresent()) {
                return networkOptional.orElseThrow(IllegalStateException::new).getConnection(pos, side).getLazyOptional().cast();
            }
        }
        return LazyOptional.empty();
    }

}
