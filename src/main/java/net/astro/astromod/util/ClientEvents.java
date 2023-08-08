package net.astro.astromod.util;

import java.util.UUID;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.astro.astromod.AstroMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

@Mod.EventBusSubscriber(modid = AstroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    private static boolean isLightningKeyPressed = false;
    private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.randomUUID();
    private static final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier(
            ATTACK_DAMAGE_MODIFIER_UUID,
            "Custom Attack Damage",
            1000.0,
            AttributeModifier.Operation.ADDITION
    );

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keybinding.lightningKey.consumeClick()) {
            PlayerEntity player = Minecraft.getInstance().player;

            if (player != null) {
                // Toggle
                ModifiableAttributeInstance movementSpeed = player.getAttribute(MOVEMENT_SPEED);
                ModifiableAttributeInstance attackDam = player.getAttribute(Attributes.ATTACK_DAMAGE);


                isLightningKeyPressed = !isLightningKeyPressed;

                if (isLightningKeyPressed) {
                    player.abilities.setWalkingSpeed(0.8f);
                    player.setSprinting(true);

                    movementSpeed.setBaseValue(1D);
                    attackDam.addTransientModifier(ATTACK_DAMAGE_MODIFIER);


                } else {
                    // Reset the speed back to normal
                    movementSpeed.setBaseValue(0.1f); // Default value is 0.1f


                }
            }
        }
    }

    @SubscribeEvent
    public static void onFOVUpdate(FOVUpdateEvent event) {
        if (isLightningKeyPressed) {
            event.setNewfov(event.getFov() * 2.0F); // Adjust the multiplier as needed
        }
    }
}
