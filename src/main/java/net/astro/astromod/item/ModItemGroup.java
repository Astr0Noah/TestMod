package net.astro.astromod.item;

import net.astro.astromod.blocks.BlockInit;
import net.astro.astromod.item.ModItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup ASTRO_MOD_GROUP = new ItemGroup("AstroModTab") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.sosshi.get());


        }
    };
}
