package net.astro.astromod.ability;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.astro.astromod.AstroMod;
import net.astro.astromod.network.InputMessage;
import net.astro.astromod.network.SyncHandler;
import net.astro.astromod.util.KeybindsInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



import static net.minecraft.advancements.criterion.EntityPredicate.Builder.entity;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

@Mod.EventBusSubscriber(modid = AstroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GravityAbility {
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event){
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if (player != null) {

            onInput(mc, event.getKey(), event.getAction());
            // Now you can use the 'world' variable to interact with the game world
        }
    }

    public static boolean isGravityToggled = false;


    private static void onInput(Minecraft mc, int key, int action) {
        if (KeybindsInit.gravKey.consumeClick()) {
            PlayerEntity player = Minecraft.getInstance().player;

            if (player != null) {
                isGravityToggled = !isGravityToggled; // Toggle the state

                if (isGravityToggled) {

                    // Code to execute when the toggle is turned on
                    SyncHandler.CHANNEL.sendToServer(new InputMessage());

                    Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Gravity toggled ON"), true);
                } else {
                    // Code to execute when the toggle is turned off
                    Minecraft.getInstance().player.displayClientMessage(new StringTextComponent("Gravity toggled OFF"), true);
                }
            }
        }
    }
}
