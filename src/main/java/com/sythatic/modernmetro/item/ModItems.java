package com.sythatic.modernmetro.item;

import com.sythatic.modernmetro.block.ModBlocks;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(com.sythatic.modernmetro.ModernMetro.MOD_ID, id);
        Item registeredItem;
        registeredItem = Registry.register(Registry.ITEM, itemID, item);
        return registeredItem;
    }

    public static final Item POWER_RAIL_1 = register(
            new BlockItem(ModBlocks.POWERRAIL1, new Item.Settings()),
            "power_rail_1"
    );

    public static final Item POWER_RAIL_2 = register(
            new BlockItem(ModBlocks.POWERRAIL2, new Item.Settings()),
            "power_rail_2"
    );

    public static final Item POWER_RAIL_3 = register(
            new BlockItem(ModBlocks.POWERRAIL3, new Item.Settings()),
            "power_rail_3"
    );

    public static final Item POWER_RAIL_4 = register(
            new BlockItem(ModBlocks.POWERRAIL4, new Item.Settings()),
            "power_rail_4"
    );

    public static final Item POWER_RAIL_5 = register(
            new BlockItem(ModBlocks.POWERRAIL5, new Item.Settings()),
            "power_rail_5"
    );

    public static void registerModItems() {
        com.sythatic.modernmetro.ModernMetro.LOGGER.info("Registering items for " + com.sythatic.modernmetro.ModernMetro.MOD_ID);
    }
}