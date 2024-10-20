package com.sythatic.modernmetro.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static Block register (Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(com.sythatic.modernmetro.ModernMetro.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static final Block POWERRAIL1 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(3.0f).noCollision()),
            "power_rail_1",
            false
    );

    public static final Block POWERRAIL2 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(3.0f).noCollision()),
            "power_rail_2",
            false
    );

    public static final Block POWERRAIL3 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(3.0f).noCollision()),
            "power_rail_3",
            false
    );

    public static final Block POWERRAIL4 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(3.0f).noCollision()),
            "power_rail_4",
            false
    );

    public static final Block POWERRAIL5 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(3.0f).noCollision()),
            "power_rail_5",
            false
    );

    public static void registerModBlocks() {
        com.sythatic.modernmetro.ModernMetro.LOGGER.info("Registering blocks for " + com.sythatic.modernmetro.ModernMetro.MOD_ID);
    }
}