package com.sythatic.modernmetro;

import com.sythatic.modernmetro.item.ModItems;
import com.sythatic.modernmetro.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModernMetro implements ModInitializer{
    public static final String MOD_ID = "modernmetro";
    public static final Logger LOGGER = LoggerFactory.getLogger("modernmetro");

    public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
            RegistryKeys.BLOCK, Identifier.of("modernmetro", "powered_rails")
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.POWERED_RAIL, ModItems.POWER_RAIL_1);
            content.addAfter(ModItems.POWER_RAIL_1, ModItems.POWER_RAIL_2);
            content.addAfter(ModItems.POWER_RAIL_2, ModItems.POWER_RAIL_3);
            content.addAfter(ModItems.POWER_RAIL_3, ModItems.POWER_RAIL_4);
            content.addAfter(ModItems.POWER_RAIL_4, ModItems.POWER_RAIL_5);
        });
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}
