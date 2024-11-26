package com.sythatic.modernmetro;

import com.sythatic.modernmetro.block.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

public class ModernMetroClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricLoader.getInstance().getModContainer("modernmetro").ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of("emissive-rails", "emissive-rails"), container, Text.literal("Emissive Rails"), ResourcePackActivationType.NORMAL);
        });
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL4, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL5, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AcceleratorRailBlock.ACCELERATORRAIL, RenderLayer.getCutout());
    }

}