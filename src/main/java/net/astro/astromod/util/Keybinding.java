package net.astro.astromod.util;

import net.astro.astromod.AstroMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static KeyBinding lightningKey;
    public static final String KEY_CATEGORY_ASTRO = "key.category."+ AstroMod.MOD_ID;
    public static final String KEY_LIGHTNING = "key.astromod.lighting";
    public static void register() {
        lightningKey = create(KEY_LIGHTNING ,GLFW.GLFW_KEY_R);
        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key." + AstroMod.MOD_ID + "." + name, key, KEY_CATEGORY_ASTRO);
    }
}
