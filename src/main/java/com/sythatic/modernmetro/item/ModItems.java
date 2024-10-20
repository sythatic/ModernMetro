package com.sythatic.modernmetro.item;

import com.sythatic.modernmetro.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(com.sythatic.modernmetro.ModernMetro.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static final Item POWER_RAIL_1 = register(
            new BlockItem(ModBlocks.POWERRAIL1, new Item.Settings()), "power_rail_1"
    );
    public static final Item POWER_RAIL_2 = register(
            new BlockItem(ModBlocks.POWERRAIL2, new Item.Settings()), "power_rail_2"
    );
    public static final Item POWER_RAIL_3 = register(
            new BlockItem(ModBlocks.POWERRAIL3, new Item.Settings()), "power_rail_3"
    );
    public static final Item POWER_RAIL_4 = register(
            new BlockItem(ModBlocks.POWERRAIL4, new Item.Settings()), "power_rail_4"
    );
    public static final Item POWER_RAIL_5 = register(
            new BlockItem(ModBlocks.POWERRAIL5, new Item.Settings()), "power_rail_5"
    );

    public static void registerItemGroupEvents() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.POWERED_RAIL, ModItems.POWER_RAIL_1);
            content.addAfter(ModItems.POWER_RAIL_1, ModItems.POWER_RAIL_2);
            content.addAfter(ModItems.POWER_RAIL_2, ModItems.POWER_RAIL_3);
            content.addAfter(ModItems.POWER_RAIL_3, ModItems.POWER_RAIL_4);
            content.addAfter(ModItems.POWER_RAIL_4, ModItems.POWER_RAIL_5);
        });
    }

    public static void registerModItems() {
        com.sythatic.modernmetro.ModernMetro.LOGGER.info("Registering items for " + com.sythatic.modernmetro.ModernMetro.MOD_ID);
    }
}