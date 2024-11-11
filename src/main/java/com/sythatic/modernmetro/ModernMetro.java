package com.sythatic.modernmetro;

import com.sythatic.modernmetro.block.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModernMetro implements ModInitializer {

    public static final String MOD_ID = "modernmetro";
    public static final Logger LOGGER = LoggerFactory.getLogger("modernmetro");
    public static final TagKey<Block> ALL_RAILS = TagKey.of(
            RegistryKeys.BLOCK, Identifier.of("modernmetro", "rails")
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.TNT_MINECART, Items.COMMAND_BLOCK_MINECART);
            content.addAfter(Items.REDSTONE_LAMP, Items.COMMAND_BLOCK);
            content.addAfter(Items.COMMAND_BLOCK, Items.CHAIN_COMMAND_BLOCK);
            content.addAfter(Items.CHAIN_COMMAND_BLOCK, Items.REPEATING_COMMAND_BLOCK);
        });
        com.sythatic.modernmetro.ModernMetro.LOGGER.info(com.sythatic.modernmetro.ModernMetro.MOD_ID + " - Re-sorted ItemGroups.REDSTONE");
        PowerRailBlock.register();
        AcceleratorRailBlock.register();
    }

}