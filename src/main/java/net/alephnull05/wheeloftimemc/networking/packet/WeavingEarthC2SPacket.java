package net.alephnull05.wheeloftimemc.networking.packet;

import net.alephnull05.wheeloftimemc.weaving.ComboTrackerProvider;
import net.alephnull05.wheeloftimemc.weaving.PlayerMagicProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WeavingEarthC2SPacket {

    public WeavingEarthC2SPacket() {

    }

    public WeavingEarthC2SPacket(FriendlyByteBuf buf) {

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

                    player.sendSystemMessage(Component.literal("Magic Level: " + magic.getMagicLevel()).withStyle(ChatFormatting.GREEN),true);

                    //add to combo
                    player.getCapability(ComboTrackerProvider.CURRENT_COMBO).ifPresent(currentCombo -> {
                        currentCombo.addThread(2);
                    });
                } else {
                    player.sendSystemMessage(Component.literal("Out of Magic").withStyle(ChatFormatting.ITALIC), true);
                }
            });

            level.playSound(null, player.getOnPos(), SoundEvents.GRASS_BREAK, SoundSource.PLAYERS, 0.75F, level.random.nextFloat()*0.1F+0.9F);

        });
        return true;
    }
}
