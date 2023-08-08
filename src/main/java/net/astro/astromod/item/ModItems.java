package net.astro.astromod.item;

import net.astro.astromod.AstroMod;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "astromod");


    public static final RegistryObject<Item> sosshi = ITEMS.register("sosshi",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
