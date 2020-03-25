package futuristicLux.engineerstech.common.block;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import futuristicLux.engineerstech.common.tileEntities.CableTileEntity;
import futuristicLux.engineerstech.common.util.NetworkCableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class CableBlock extends Block implements IWaterLoggable{

	//cable direcctions
	private static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	private static final BooleanProperty EAST = BlockStateProperties.EAST;
	private static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	private static final BooleanProperty WEST = BlockStateProperties.WEST;
	private static final BooleanProperty UP = BlockStateProperties.UP;
	private static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p_203421_0_) -> {
	      p_203421_0_.put(Direction.NORTH, NORTH);
	      p_203421_0_.put(Direction.EAST, EAST);
	      p_203421_0_.put(Direction.SOUTH, SOUTH);
	      p_203421_0_.put(Direction.WEST, WEST);
	      p_203421_0_.put(Direction.UP, UP);
	      p_203421_0_.put(Direction.DOWN, DOWN);
	   });
	
	//cable shapes
	public static final AxisAlignedBB BASIC_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 6D/16D, 10D/16D, 10D/16D, 10D/16D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 0D, 10D/16D, 10D/16D, 6D/16D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 10D/16D, 10D/16D, 10D/16D, 16D/16D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(10D/16D, 6D/16D, 6D/16D, 16D/16D, 10D/16D, 10D/16D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0D, 6D/16D, 6D/16D, 6D/16D, 10D/16D, 10D/16D);
	public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(6D/16D, 10D/16D, 6D/16D, 10D/16D, 16D/16D, 10D/16D);
	public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(6D/16D, 0D, 6D/16D, 10D/16D, 6D/16D, 10D/16D);
	public static VoxelShape shape;
	protected final VoxelShape[] shapes;

	public CableBlock(Properties properties) {
		super(properties);
		shape = VoxelShapes.create(BASIC_AABB);
		shapes = new VoxelShape[6];
		shapes[0] = VoxelShapes.create(NORTH_AABB);
		shapes[1] = VoxelShapes.create(EAST_AABB);
		shapes[2] = VoxelShapes.create(SOUTH_AABB);
		shapes[3] = VoxelShapes.create(WEST_AABB);
		shapes[4] = VoxelShapes.create(UP_AABB);
		shapes[5] = VoxelShapes.create(DOWN_AABB);
		setDefaultState(stateContainer.getBaseState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, false).with(WATERLOGGED, false));
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN,WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		buildShape(state);
		return shape; 
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		buildShape(state);
		return shape; 
	}
	
	private VoxelShape buildShape(BlockState state) {
		shape = VoxelShapes.create(BASIC_AABB);
		if(state.get(NORTH)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[0],IBooleanFunction.OR);
		}
		if(state.get(EAST)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[1],IBooleanFunction.OR);
		}
		if(state.get(SOUTH)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[2],IBooleanFunction.OR);
		}
		if(state.get(WEST)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[3],IBooleanFunction.OR);
		}
		if(state.get(UP)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[4],IBooleanFunction.OR);
		}
		if(state.get(DOWN)) {
			shape = VoxelShapes.combineAndSimplify(shape, shapes[5],IBooleanFunction.OR);
		}
		
		
		return shape;
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	      IBlockReader iblockreader = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
	      BlockPos blockpos1 = blockpos.north();
	      BlockPos blockpos2 = blockpos.east();
	      BlockPos blockpos3 = blockpos.south();
	      BlockPos blockpos4 = blockpos.west();
	      BlockPos blockpos5 = blockpos.up();
	      BlockPos blockpos6 = blockpos.down();
	      BlockState blockstate = iblockreader.getBlockState(blockpos1);
	      BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
	      BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
	      BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
	      BlockState blockstate4 = iblockreader.getBlockState(blockpos5);
	      BlockState blockstate5 = iblockreader.getBlockState(blockpos6);
	      return super.getStateForPlacement(context).with(NORTH, canAttach(blockstate.getBlock())).with(EAST, canAttach(blockstate1.getBlock())).with(SOUTH, canAttach(blockstate2.getBlock())).with(WEST, canAttach(blockstate3.getBlock())).with(UP, canAttach(blockstate4.getBlock())).with(DOWN, canAttach(blockstate5.getBlock())).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	    
		if (stateIn.get(WATERLOGGED)) {
	      worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	    }
		
	    return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), canAttach(facingState.getBlock()));
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		buildShape(state);
		return shape; 
	}
	
	public IFluidState getFluidState(BlockState state) {
	   return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	   return !state.get(WATERLOGGED);
	}
	
	protected boolean canAttach(Block attach) {
		for(Block block : NetworkCableList.cabling) {
			if(block.equals(attach)) return true;
		}
		return false;
	}
	
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CableTileEntity();
    }
	
	
}
