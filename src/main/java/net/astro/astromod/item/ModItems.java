package net.astro.astromod.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "astromod");


    public static final RegistryObject<Item> sosshi = ITEMS.register("sosshi",
            () -> new Item(new Item.Properties().tab(ModItemGroup.ASTRO_MOD_GROUP)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
