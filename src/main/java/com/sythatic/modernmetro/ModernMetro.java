package com.sythatic.modernmetro;

import com.sythatic.modernmetro.item.ModItems;
import com.sythatic.modernmetro.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModernMetro implements ModInitializer {
    public static final String MOD_ID = "modernmetro";
    public static final Logger LOGGER = LoggerFactory.getLogger("modernmetro");

    // Adjust for 1.18.2 registry system
    public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
            Registry.BLOCK_KEY, new Identifier("modernmetro", "powered_rails")
    );

    @Override
    public void onInitialize() {
        // Custom Item Group
        ItemGroup MOD_GROUP = FabricItemGroupBuilder.create(
                        new Identifier(MOD_ID, "modernmetro_group"))
                .icon(() -> new ItemStack(ModItems.POWER_RAIL_1))
                .appendItems(stacks -> {
                    stacks.add(new ItemStack(Items.POWERED_RAIL));
                    stacks.add(new ItemStack(ModItems.POWER_RAIL_1));
                    stacks.add(new ItemStack(ModItems.POWER_RAIL_2));
                    stacks.add(new ItemStack(ModItems.POWER_RAIL_3));
                    stacks.add(new ItemStack(ModItems.POWER_RAIL_4));
                    stacks.add(new ItemStack(ModItems.POWER_RAIL_5));
                }).build();

        // Register mod items and blocks
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}
