package futuristicLux.engineerstech.common.network;

import java.util.*;

import futuristicLux.engineerstech.common.tileEntities.CableTileEntity;
import futuristicLux.engineerstech.common.util.EnergyUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.ConnectionType;

public class CableNetwork implements IEnergyStorage{
	
	public int TRANSFER_PER_CONNECTION;

    private final IWorldReader world;
    private final Map<BlockPos, Set<Connection>> connections = new HashMap<>();
    private boolean connectionsBuilt;
    private int energyStored;
    
    private CableNetwork(IWorldReader world, Set<BlockPos> cables, int energyStored) {
        this.world = world;
        cables.forEach(pos -> connections.put(pos, Collections.emptySet()));
        this.energyStored = energyStored;
    }
    
    public boolean contains(IWorldReader world, BlockPos pos) {
        return this.world == world && connections.containsKey(pos);
    }
    
    public int getCableCount() {
        return connections.size();
    }

    private void updateCableEnergy() {
    	int energyPerCable = energyStored / getCableCount();
        connections.keySet().forEach(p -> {
            TileEntity tileEntity = world.getTileEntity(p);
            if (tileEntity instanceof CableTileEntity) {
                ((CableTileEntity) tileEntity).energyStored = energyPerCable;
            }
        });
    }
    
    void invalidate() {
        connections.values().forEach(set -> set.forEach(con -> con.getLazyOptional().invalidate()));
    }
    
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		buildConnections();
        int received = Math.min(getMaxEnergyStored() - energyStored, Math.min(maxReceive, TRANSFER_PER_CONNECTION));
        if (received > 0) {
            if (!simulate) {
                energyStored += received;
                updateCableEnergy();
            }
        }
        return received;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		buildConnections();
        int extracted = Math.min(energyStored, Math.min(maxExtract, TRANSFER_PER_CONNECTION));
        if (extracted > 0) {
            if (!simulate) {
                energyStored -= extracted;
                updateCableEnergy();
            }
        }
        return extracted;
	}
	
	void sendEnergy() {
        buildConnections();

        // Send stored energy to connected blocks
        for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
            BlockPos pos = entry.getKey();
            Set<Connection> connections = entry.getValue();
            for (Connection con : connections) {
                 IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(con.side));
                 if (energy != null && energy.canReceive()) {
                     int toSend = extractEnergy(TRANSFER_PER_CONNECTION, true);
                     int accepted = energy.receiveEnergy(toSend, false);
                     extractEnergy(accepted, false);
                }
            }
        }
    }

	@Override
	public int getEnergyStored() {
		return energyStored;
	}

	@Override
	public int getMaxEnergyStored() {
		return TRANSFER_PER_CONNECTION * connections.size();
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return true;
	}
	
	static CableNetwork buildNetwork(IWorldReader world, BlockPos pos) {
        Set<BlockPos> wires = buildCableSet(world, pos);
        int energyStored = wires.stream().mapToInt(p -> {
            TileEntity tileEntity = world.getTileEntity(p);
            return tileEntity instanceof CableTileEntity ? ((CableTileEntity) tileEntity).energyStored : 0;
        }).sum();
        return new CableNetwork(world, wires, energyStored);
    }
	
	private static Set<BlockPos> buildCableSet(IWorldReader world, BlockPos pos) {
        return buildCableSet(world, pos, new HashSet<>());
    }
	
	private static Set<BlockPos> buildCableSet(IWorldReader world, BlockPos pos, Set<BlockPos> set) {
        set.add(pos);
        for (Direction side : Direction.values()) {
            BlockPos pos1 = pos.offset(side);
            if (!set.contains(pos1) && world.getTileEntity(pos1) instanceof CableTileEntity) {
                set.add(pos1);
                set.addAll(buildCableSet(world, pos1, set));
            }
        }
        return set;
    }
	
	public Connection getConnection(BlockPos pos, Direction side) {
		if (connections.containsKey(pos)) {
            for (Connection connection : connections.get(pos)) {
                if (connection.side == side) {
                    return connection;
                }
            }
        }
        return new Connection(this, side);
	}
	
	private void buildConnections() {
        // Determine all connections. This will be done once the connections are actually needed.
        if (!connectionsBuilt) {
            connections.keySet().forEach(p -> connections.put(p, getConnections(world, p)));
            connectionsBuilt = true;
        }
    }
	
	private Set<Connection> getConnections(IBlockReader world, BlockPos pos) {
        // Get all connections for the wire at pos
        Set<Connection> connections = new HashSet<>();
        for (Direction direction : Direction.values()) {
            TileEntity te = world.getTileEntity(pos.offset(direction));
            if (te != null && !(te instanceof CableTileEntity) && te.getCapability(CapabilityEnergy.ENERGY).isPresent()) {
                connections.add(new Connection(this, direction));
            }
        }
        return connections;
    }
	
	@Override
    public String toString() {
        return String.format("WireNetwork %s, %d wires, %,d FE", Integer.toHexString(hashCode()), connections.size(), energyStored);
    }
	
	public static class Connection implements IEnergyStorage {
        private final CableNetwork network;
        private final Direction side;
        private final LazyOptional<Connection> lazyOptional;

        Connection(CableNetwork network, Direction side) {
            this.network = network;
            this.side = side;
            this.lazyOptional = LazyOptional.of(() -> this);
        }

        public LazyOptional<Connection> getLazyOptional() {
            return lazyOptional;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            if (!canReceive()) {
                return 0;
            }
            return network.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            if (!canExtract()) {
                return 0;
            }
            return network.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int getEnergyStored() {
            return network.energyStored;
        }

        @Override
        public int getMaxEnergyStored() {
            return network.getMaxEnergyStored();
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    }

}
