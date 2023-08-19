package net.astro.astromod.util;

import java.util.UUID;

import net.astro.astromod.AstroMod;
import net.astro.astromod.network.EffectInputMessage;
import net.astro.astromod.network.InputMessage;
import net.astro.astromod.network.SyncHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

@Mod.EventBusSubscriber(modid = AstroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event){
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if (player != null) {

            onInput(mc, event.getKey(), event.getAction());
            // Now you can use the 'world' variable to interact with the game world
        }
    }



    private static void onInput(Minecraft mc, int key, int action){
        if(KeybindsInit.lightningKey.consumeClick()){
            SyncHandler.CHANNEL.sendToServer(new EffectInputMessage());



        }

    }
}
