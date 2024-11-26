package com.sythatic.modernmetro;

import com.sythatic.modernmetro.block.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ModernMetroClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL4, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PowerRailBlock.POWERRAIL5, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(AcceleratorRailBlock.ACCELERATORRAIL, RenderLayer.getCutout());
    }

}