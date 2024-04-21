package abby.crystallised;

import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class CrystallisedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		GemType.MAP.forEach((s, gemType) -> {
			BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.blockMap.get(gemType.getName() + "_egg"), RenderLayer.getTranslucent());
		});
	}
}