package futuristicLux.engineerstech.common.block;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class CableBlock extends Block implements IWaterLoggable{

	//cable direcctions
	private static final Direction[] FACING_VALUES = Direction.values();
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	//public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p_203421_0_) -> {
	   p_203421_0_.put(Direction.NORTH, NORTH);
	   p_203421_0_.put(Direction.EAST, EAST);
	   p_203421_0_.put(Direction.SOUTH, SOUTH);
	   p_203421_0_.put(Direction.WEST, WEST);
	   p_203421_0_.put(Direction.UP, UP);
	   p_203421_0_.put(Direction.DOWN, DOWN);
	});
	  
	//cable shapes
	public static final AxisAlignedBB BASIC_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 6D/16D, 10D/16D, 10D/16D, 10D/16D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 12D/16D, 10D/16D, 10D/16D, 16D/16D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(6D/16D, 6D/16D, 0D, 10D/16D, 10D/16D, 6D/16D);
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
		setDefaultState(stateContainer.getBaseState());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
		//builder.add(BlockStateProperties.WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		//buildShape(state);
		return shape; 
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		buildShape(state);
		return shape; 
	}
	
	private VoxelShape buildShape(BlockState state) {
		/*
		for(int i = 0; i < FACING_VALUES.length; ++i) {
	         if (state.get(FACING_TO_PROPERTY_MAP.get(FACING_VALUES[i]))) {
	            VoxelShapes.combineAndSimplify(shape, shapes[i],IBooleanFunction.OR);
	         }
	      }*/
		
		if(state.get(NORTH)) {
			VoxelShapes.combineAndSimplify(shape, shapes[0],IBooleanFunction.OR);
		}
		if(state.get(EAST)) {
			VoxelShapes.combineAndSimplify(shape, shapes[1],IBooleanFunction.OR);
		}
		if(state.get(SOUTH)) {
			VoxelShapes.combineAndSimplify(shape, shapes[2],IBooleanFunction.OR);
		}
		if(state.get(WEST)) {
			VoxelShapes.combineAndSimplify(shape, shapes[3],IBooleanFunction.OR);
		}
		if(state.get(UP)) {
			VoxelShapes.combineAndSimplify(shape, shapes[4],IBooleanFunction.OR);
		}
		if(state.get(DOWN)) {
			VoxelShapes.combineAndSimplify(shape, shapes[5],IBooleanFunction.OR);
		}
		
		
		return shape;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		//buildShape(state);
		return shape; 
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	   return true;
	}
}
