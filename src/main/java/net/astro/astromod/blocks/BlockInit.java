package net.astro.astromod.blocks;

import net.astro.astromod.AstroMod;
import net.astro.astromod.item.ModItemGroup;
import net.astro.astromod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;


public class BlockInit{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AstroMod.MOD_ID);

    public  static final RegistryObject<Block> CREATION_BLOCK = registerBlock("creation_block",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE,
            MaterialColor.TERRACOTTA_WHITE).strength(1000).sound(SoundType.BONE_BLOCK).lightLevel(state -> 15)
                    ));

    private static <T extends Block>RegistryObject<T>registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockitem(name, toReturn);
        return toReturn;

    }
    private static <T extends Block>void registerBlockitem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.ASTRO_MOD_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }



}
