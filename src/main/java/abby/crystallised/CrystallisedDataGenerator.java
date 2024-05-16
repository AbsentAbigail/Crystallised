package abby.crystallised;

import abby.crystallised.datagen.LanguageFileGenerator;
import abby.crystallised.datagen.ModelGenerator;
import abby.crystallised.datagen.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.DynamicRegistryManager;

public class CrystallisedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(LanguageFileGenerator::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
	}
}
