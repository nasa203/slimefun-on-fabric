package com.ice;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class ModblockControllerBlock extends Block {
    public ModblockControllerBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        boolean match = tryFindMultiblock(world, pos);
        if (match) {
            player.sendMessage(Text.literal("Multiblock formed!"), false);
            activateMachine(world, pos);
        } else {
            player.sendMessage(Text.literal("Invalid structure"), false);
        }
        return ActionResult.SUCCESS;
    }
    private boolean tryFindMultiblock(World world, BlockPos pos) {
        return
            world.getBlockState(pos.add(0, 0, -1)).isOf(Blocks.STONE) &&
            world.getBlockState(pos.add(0, 0,  1)).isOf(Blocks.STONE) &&
            world.getBlockState(pos.add(1, 0, 0)).isOf(Blocks.STONE) &&
            world.getBlockState(pos.add(-1, 0, 0)).isOf(Blocks.STONE) &&
            world.getBlockState(pos.add(1, 1, 1)).isOf(Blocks.IRON_BLOCK) &&
            world.getBlockState(pos.add(-1, 1, 1)).isOf(Blocks.IRON_BLOCK) &&
            world.getBlockState(pos.add(1, 1, -1)).isOf(Blocks.IRON_BLOCK) &&
            world.getBlockState(pos.add(-1, 1, -1)).isOf(Blocks.IRON_BLOCK);
    }
    private void activateMachine(World world, BlockPos pos) {
		
    }
}