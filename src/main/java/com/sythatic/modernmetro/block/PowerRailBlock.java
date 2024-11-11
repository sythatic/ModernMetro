package com.sythatic.modernmetro.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class PowerRailBlock {

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

    public static final Block POWERRAIL1 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.COPPER).strength(1.4f).noCollision()),
            "power_rail_1",
            false
    );

    public static final Block POWERRAIL2 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.NETHERITE).strength(1.4f).noCollision()),
            "power_rail_2",
            false
    );

    public static final Block POWERRAIL3 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.COPPER_BULB).strength(2.5f).noCollision()),
            "power_rail_3",
            false
    );

    public static final Block POWERRAIL4 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.COPPER_BULB).strength(2.5f).noCollision()),
            "power_rail_4",
            false
    );

    public static final Block POWERRAIL5 = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.NETHERITE).strength(5.0f).noCollision()),
            "power_rail_5",
            false
    );

    public static final Item POWER_RAIL_1 = register(
            new BlockItem(PowerRailBlock.POWERRAIL1, new Item.Settings()), "power_rail_1"
    );

    public static final Item POWER_RAIL_2 = register(
            new BlockItem(PowerRailBlock.POWERRAIL2, new Item.Settings()), "power_rail_2"
    );

    public static final Item POWER_RAIL_3 = register(
            new BlockItem(PowerRailBlock.POWERRAIL3, new Item.Settings()), "power_rail_3"
    );

    public static final Item POWER_RAIL_4 = register(
            new BlockItem(PowerRailBlock.POWERRAIL4, new Item.Settings()), "power_rail_4"
    );

    public static final Item POWER_RAIL_5 = register(
            new BlockItem(PowerRailBlock.POWERRAIL5, new Item.Settings()), "power_rail_5"
    );

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.POWERED_RAIL, PowerRailBlock.POWER_RAIL_1);
            content.addAfter(PowerRailBlock.POWER_RAIL_1, PowerRailBlock.POWER_RAIL_2);
            content.addAfter(PowerRailBlock.POWER_RAIL_2, PowerRailBlock.POWER_RAIL_3);
            content.addAfter(PowerRailBlock.POWER_RAIL_3, PowerRailBlock.POWER_RAIL_4);
            content.addAfter(PowerRailBlock.POWER_RAIL_4, PowerRailBlock.POWER_RAIL_5);
        });
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:power_rail_1");
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:power_rail_2");
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:power_rail_3");
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:power_rail_4");
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:power_rail_5");
    }

}