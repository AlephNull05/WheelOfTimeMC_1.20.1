package net.alephnull05.wheeloftimemc.networking.packet;

import net.alephnull05.wheeloftimemc.weaving.PlayerMagic;
import net.alephnull05.wheeloftimemc.weaving.PlayerMagicProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WeavingFireC2SPacket {

    public WeavingFireC2SPacket() {

    }

    public WeavingFireC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();

            //decrease magic level
            player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(magic -> {
                if (magic.getMagicLevel() > 0) {
                    magic.subMagic(1);

                    player.sendSystemMessage(Component.literal("Magic Level: " + magic.getMagicLevel()));

                    //add to combo
                } else {
                    player.sendSystemMessage(Component.literal("Out of Magic"));
                }
            });

            level.playSound(null, player.getOnPos(), SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, 0.75F, level.random.nextFloat()*0.1F+0.9F);

        });
        return true;
    }
}
