package lilypuree.wandering_trapper;

import lilypuree.wandering_trapper.core.RegistryNames;
import lilypuree.wandering_trapper.core.RegistryObjects;
import lilypuree.wandering_trapper.core.RegistrySuppliers;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;

public class Registration {

    public static void onRegistration() {
        RegistryObjects.initEntities();
        Registry.register(Registry.ENTITY_TYPE, RegistryNames.WANDERING_TRAPPER, RegistryObjects.WANDERING_TRAPPER);
        Registry.register(Registry.ENTITY_TYPE, RegistryNames.TRAPPER_DOG, RegistryObjects.TRAPPER_DOG);


        RegistryObjects.initBlocks();
        Registry.register(Registry.BLOCK, RegistryNames.PELT_SCRAPING_LOG, RegistryObjects.PELT_SCRAPING_LOG);

        RegistryObjects.initItems();
        Registry.register(Registry.ITEM, RegistryNames.PELT_SCRAPING_LOG, RegistryObjects.PELT_SCRAPING_LOG_ITEM);
        Registry.register(Registry.ITEM, RegistryNames.BEAVER_PELT, RegistryObjects.BEAVER_PELT);
        Registry.register(Registry.ITEM, RegistryNames.MARTEN_PELT, RegistryObjects.MARTEN_PELT);
        Registry.register(Registry.ITEM, RegistryNames.MINK_PELT, RegistryObjects.MINK_PELT);
        Registry.register(Registry.ITEM, RegistryNames.FOX_PELT, RegistryObjects.FOX_PELT);
        Registry.register(Registry.ITEM, RegistryNames.SNOW_FOX_PELT, RegistryObjects.SNOW_FOX_PELT);
        Registry.register(Registry.ITEM, RegistryNames.POLARBEAR_PELT, RegistryObjects.POLARBEAR_PELT);
        Registry.register(Registry.ITEM, RegistryNames.WANDERING_TRAPPER_SPAWN_EGG, RegistryObjects.WANDERING_TRAPPER_SPAWN_EGG);


        RegistryObjects.FURRIER_POI = PointOfInterestHelper.register(RegistryNames.FURRIER, 1, 1, RegistryObjects.PELT_SCRAPING_LOG);

        RegistryObjects.FURRIER = VillagerProfessionBuilder.create().id(RegistryNames.FURRIER).workstation(RegistryObjects.FURRIER_POI).workSound(SoundEvents.VILLAGER_WORK_LEATHERWORKER).build();
        Registry.register(Registry.VILLAGER_PROFESSION, RegistryNames.FURRIER, RegistryObjects.FURRIER);
    }
}
