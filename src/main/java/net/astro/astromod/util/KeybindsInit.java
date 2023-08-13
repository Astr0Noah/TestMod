package net.astro.astromod.util;

import net.astro.astromod.AstroMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;



import java.awt.event.KeyEvent;
@OnlyIn(Dist.CLIENT)
public class KeybindsInit {
    public static KeyBinding lightningKey;
    public static KeyBinding gravKey;

    public static void register(final FMLClientSetupEvent event){
        lightningKey = create("lightning_key", KeyEvent.VK_R);

        ClientRegistry.registerKeyBinding(lightningKey);
        gravKey = create("grav_key", KeyEvent.VK_F);

        ClientRegistry.registerKeyBinding(gravKey);



    }
    private static KeyBinding create(String name, int key){
        return new KeyBinding("key."+ AstroMod.MOD_ID + "."+ name, key,"key.category."+AstroMod.MOD_ID);
    }
}
