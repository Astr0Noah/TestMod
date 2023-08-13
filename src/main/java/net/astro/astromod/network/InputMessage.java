package net.astro.astromod.network;


import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


public class InputMessage {
    public int key;

    public InputMessage() {
    }

    public InputMessage(int key) {
        this.key = key;
    }

    public static void encode(InputMessage message, PacketBuffer buffer) {
        buffer.writeInt(message.key);
    }



    public static InputMessage decode(PacketBuffer buffer){
        return new InputMessage(buffer.readInt());
    }
    public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(()->{
            ServerPlayerEntity player = context.getSender();
            //code for server goes here
            player.addItem(new ItemStack(Items.DIAMOND));





        });
        context.setPacketHandled(true);


    }








}
