package com.sythatic.modernmetro;

import com.sythatic.modernmetro.item.ModItems;
import com.sythatic.modernmetro.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModernMetro implements ModInitializer {
    public static final String MOD_ID = "modernmetro";
    public static final Logger LOGGER = LoggerFactory.getLogger("modernmetro");

    public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
            RegistryKeys.BLOCK, Identifier.of("modernmetro", "powered_rails")
    );

    @Override
    public void onInitialize() {
        ModItems.registerItemGroupEvents();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}