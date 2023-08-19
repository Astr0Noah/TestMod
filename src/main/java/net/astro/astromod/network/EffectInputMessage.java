package net.astro.astromod.network;


import net.astro.astromod.ability.GravityAbility;
import net.astro.astromod.blocks.BlockInit;
import net.astro.astromod.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


import static net.astro.astromod.ability.GravityAbility.isGravityToggled;


public class EffectInputMessage {
    public int key;

    public EffectInputMessage() {
    }

    public EffectInputMessage(int key) {
        this.key = key;
    }

    public static void encode(EffectInputMessage message, PacketBuffer buffer) {
        buffer.writeInt(message.key);
    }


    public static EffectInputMessage decode(PacketBuffer buffer) {
        return new EffectInputMessage(buffer.readInt());
    }

    public static void handle(EffectInputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            //code for server goes here


            LivingEntity target =  player.getLastHurtMob();
            float targhealth = target.getHealth();
            BlockPos targetStandingBlock = target.blockPosition().below();
            World world= target.level;
            if(targhealth <= 10){
                if (target instanceof LivingEntity) {
                    applySlowdownEffect((LivingEntity) target);
                    target.playSound(ModSounds.IMMOBILE.get(), .1f, .4f);
                    BlockState newState = Blocks.OBSIDIAN.defaultBlockState();
                    world.setBlockAndUpdate(targetStandingBlock, newState);

                }
            }
            else{
                String mesage = "Target health: " + targhealth;

                player.sendMessage(new StringTextComponent(mesage),player.getUUID());
            }







        });
        context.setPacketHandled(true);


    }

    private static void applySlowdownEffect(LivingEntity target) {
        target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 50, false,false));
    }





}











