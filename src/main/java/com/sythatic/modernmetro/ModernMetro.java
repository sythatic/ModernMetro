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

public class ModernMetro implements ModInitializer {
	public static final String MOD_ID = "modernmetro";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
			RegistryKeys.BLOCK, Identifier.of("modernmetro", "powered_rails")
	);

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.addAfter(Items.RAIL, ModItems.IRON_RAIL);
			content.addAfter(ModItems.IRON_RAIL, Items.POWERED_RAIL);
			content.addAfter(Items.POWERED_RAIL, ModItems.DIAMOND_RAIL);
			content.addAfter(ModItems.DIAMOND_RAIL, ModItems.NETHERITE_RAIL);
			content.addAfter(ModItems.NETHERITE_RAIL, ModItems.EMERALD_RAIL);
		});
		// ^^ this should really be in the ModItems class but I'm not really bothered as I have 3 items
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}