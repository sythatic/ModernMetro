package com.sythatic.modernmetro.item;

import com.sythatic.modernmetro.ModernMetro;
import com.sythatic.modernmetro.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(ModernMetro.MOD_ID, id);
        Item registeredItem;
        registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static final Item IRON_RAIL = register(
            new BlockItem(ModBlocks.IRONRAIL, new Item.Settings()),
            "iron_rail"
    );

    public static final Item DIAMOND_RAIL = register(
            new BlockItem(ModBlocks.DIAMONDRAIL, new Item.Settings()),
            "diamond_rail"
    );

    public static final Item EMERALD_RAIL = register(
            new BlockItem(ModBlocks.EMERALDRAIL, new Item.Settings()),
            "emerald_rail"
    );

    public static final Item NETHERITE_RAIL = register(
            new BlockItem(ModBlocks.NETHERITERAIL, new Item.Settings()),
            "netherite_rail"
    );

    public static void registerModItems() {
        ModernMetro.LOGGER.info("Registering items for " + ModernMetro.MOD_ID);
    }
}