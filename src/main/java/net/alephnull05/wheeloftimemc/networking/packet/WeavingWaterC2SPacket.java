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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class WeavingWaterC2SPacket {

    public WeavingWaterC2SPacket() {

    }

    public WeavingWaterC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            AtomicBoolean check = new AtomicBoolean(false);

            //decrease magic level
            player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(magic -> {
                if (magic.getMagicLevel() > 0) {
                    magic.subMagic(1);

                    check.set(true);

                    player.sendSystemMessage(Component.literal("Magic Level: " + magic.getMagicLevel()).withStyle(ChatFormatting.BLUE),true);

                    //add to combo

                } else {
                    player.sendSystemMessage(Component.literal("Out of Magic").withStyle(ChatFormatting.ITALIC));
                }
            });

            player.getCapability(ComboTrackerProvider.CURRENT_COMBO).ifPresent(currentCombo -> {
                currentCombo.addThread(3);
                player.sendSystemMessage(Component.literal("Combo: " + currentCombo.getCurrentCombo().toString()));
            });

            level.playSound(null, player.getOnPos(), SoundEvents.WATER_AMBIENT, SoundSource.PLAYERS, 0.75F, level.random.nextFloat()*0.1F+0.9F);

        });
        return true;
    }
}
