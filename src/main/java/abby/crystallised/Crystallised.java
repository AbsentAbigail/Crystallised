package abby.crystallised;

import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.blocks.blockentities.ModBlockEntities;
import abby.crystallised.items.ModItems;
import abby.crystallised.miscellaneous.ModItemGroups;
import net.fabricmc.api.ModInitializer;

public class Crystallised implements ModInitializer {
	@Override
	public void onInitialize() {
		new ModItems();
		new ModBlocks();
		new ModItemGroups();
		new ModBlockEntities();
	}
}