package net.astro.astromod.item;

import net.astro.astromod.AstroMod;
import net.astro.astromod.blocks.BlockInit;
import net.astro.astromod.blocks.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {
    @ObjectHolder("yourmod:glowing_concrete")
    public static final Item GLOWING_CONCRETE_ITEM = null;
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "astromod");



    public static final RegistryObject<Item> sosshi = ITEMS.register("sosshi",
            () -> new Item(new Item.Properties().tab(ModItemGroup.ASTRO_MOD_GROUP)));













    public static void register(IEventBus eventBus){

        ITEMS.register(eventBus);

    }
}
