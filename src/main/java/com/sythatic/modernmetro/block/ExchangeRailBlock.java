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

public class ExchangeRailBlock {

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

    public static final Block EXCHANGERAIL = register(
            new PoweredRailBlock(AbstractBlock.Settings.copy(Blocks.POWERED_RAIL).sounds(BlockSoundGroup.METAL).strength(1.4f).noCollision()),
            "exchange_rail",
            false
    );

    public static final Item EXCHANGE_RAIL = register(
            new BlockItem(ExchangeRailBlock.EXCHANGERAIL, new Item.Settings()), "exchange_rail"
    );

    public static void registerModBlock() {
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Registered block:exchange_rail");
    }

}