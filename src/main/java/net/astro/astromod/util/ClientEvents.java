package net.astro.astromod.util;

import java.util.UUID;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.astro.astromod.AstroMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

@Mod.EventBusSubscriber(modid = AstroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    private static boolean isLightningKeyPressed = false;
    public static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.randomUUID();
    public static final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier(
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


                isLightningKeyPressed = !isLightningKeyPressed;

                if (isLightningKeyPressed) {
                    player.abilities.setWalkingSpeed(0.8f);
                    player.setSprinting(true);


                    movementSpeed.setBaseValue(1D);


                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, /*duration in ticks*/ 200, /*amplifier*/ 1000));
                } else {
                    // Reset the speed back to normal
                    movementSpeed.setBaseValue(0.1f); // Default value is 0.1f

                    player.removeEffect(Effects.DAMAGE_BOOST);

                        }
                    }
                }
            }
}
