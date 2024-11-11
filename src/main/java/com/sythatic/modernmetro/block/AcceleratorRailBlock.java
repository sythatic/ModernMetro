package com.sythatic.modernmetro.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;

public class AcceleratorRailBlock extends PoweredRailBlock {

    public static Block register (Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(com.sythatic.modernmetro.ModernMetro.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(com.sythatic.modernmetro.ModernMetro.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static final Block ACCELERATORRAIL = register(
            new AcceleratorRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.LODESTONE).strength(1.4f).noCollision()),
            "accelerator_rail",
            false
    );

    public static final Item ACCELERATOR_RAIL = register(
            new BlockItem(AcceleratorRailBlock.ACCELERATORRAIL, new Item.Settings()), "accelerator_rail"
    );


    public AcceleratorRailBlock(AbstractBlock.Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(Properties.INVERTED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        boolean isInverted = switch(state.get(getShapeProperty())) {
            case EAST_WEST -> ctx.getHorizontalPlayerFacing() == Direction.EAST;
            case NORTH_SOUTH -> ctx.getHorizontalPlayerFacing() == Direction.SOUTH;
            default -> throw new UnsupportedOperationException();
        };
        return state.with(Properties.INVERTED, isInverted);
    }

    public Vec3d getPushVector(BlockState state) {
        return switch(state.get(getShapeProperty())){
            case ASCENDING_EAST, ASCENDING_WEST, EAST_WEST -> new Vec3d(.5,0,0);
            case ASCENDING_SOUTH, ASCENDING_NORTH, NORTH_SOUTH -> new Vec3d(0,0,.5);
            default -> throw new UnsupportedOperationException();
        };
    }

    public void affectMinecart(AbstractMinecartEntity minecart, BlockState state){
        Vec3d pushForce = getPushVector(state);
        if(state.get(Properties.INVERTED))
            pushForce = pushForce.negate();
        minecart.setVelocity(minecart.getVelocity().add(pushForce));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, POWERED, WATERLOGGED, Properties.INVERTED);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(PowerRailBlock.POWER_RAIL_5, AcceleratorRailBlock.ACCELERATOR_RAIL);
        });
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:accelerator_rail");
    }

}