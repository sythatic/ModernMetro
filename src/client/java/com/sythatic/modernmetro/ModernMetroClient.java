package com.sythatic.modernmetro;

import com.sythatic.modernmetro.block.ModBlocks;
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
            ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("shader-support", "shader-support"), container, "Emissive Rails", ResourcePackActivationType.NORMAL);
        });
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWERRAIL1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWERRAIL2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWERRAIL3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWERRAIL4, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWERRAIL5, RenderLayer.getCutout());
    }
}