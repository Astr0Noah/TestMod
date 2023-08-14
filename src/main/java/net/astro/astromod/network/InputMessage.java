package net.astro.astromod.network;


import net.astro.astromod.ability.GravityAbility;
import net.astro.astromod.blocks.BlockInit;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


import static net.astro.astromod.ability.GravityAbility.isGravityToggled;


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


    public static InputMessage decode(PacketBuffer buffer) {
        return new InputMessage(buffer.readInt());
    }

    public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            //code for server goes here
            player.addItem(new ItemStack(Items.DIAMOND));


            applyIceAround(player, 5);


        });
        context.setPacketHandled(true);


    }




    private static void applyIceAround(PlayerEntity player, int radius) {
        World world = player.level;
        BlockPos playerPos = player.blockPosition();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -1; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = playerPos.offset(x, y, z);

                    // Skip the player's position
                    if (blockPos.equals(playerPos)) {
                        continue;
                    }

                    BlockState currentState = world.getBlockState(blockPos);


                    if (currentState.getBlock() != BlockInit.CREATION_BLOCK.get()){ // Check if the block is not already ice
                        BlockState newState = BlockInit.CREATION_BLOCK.get().defaultBlockState();
                        world.setBlockAndUpdate(blockPos, newState);
                    }
                }

            }
        }




        BlockPos blockPos = playerPos;
        BlockPos Airpos = playerPos.offset(0, 1, 0);


        world.setBlockAndUpdate(Airpos, Blocks.AIR.defaultBlockState());
        world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
        airr(player, 4);
    }

    private static void airr(PlayerEntity player, int radius) {
        World world = player.level;
        BlockPos playerPos = player.blockPosition();

        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = playerPos.offset(x, y, z);

                    // Skip the player's position
                    if (blockPos.equals(playerPos)) {
                        continue;
                    }

                    BlockState currentState = world.getBlockState(blockPos);

                    if (currentState.getBlock() != Blocks.AIR) { // Check if the block is not already ice
                        BlockState newState = Blocks.AIR.defaultBlockState();
                        world.setBlockAndUpdate(blockPos, newState);
                    }
                }

            }
        }

        BlockPos blockPos = playerPos;
        BlockPos Airpos = playerPos.offset(0, 1, 0);


        world.setBlockAndUpdate(Airpos, Blocks.AIR.defaultBlockState());
        world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
    }

}











