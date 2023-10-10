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

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WeavingC2SPacket {

    public WeavingC2SPacket() {

    }

    public WeavingC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            //1=fire 2=earth 3=water 4=air 5=spirit
            final int[] FIREBALL_COMBO = {1,5};
            List<Integer> FIREBALL = Arrays.stream(FIREBALL_COMBO).boxed().toList();

            player.getCapability(ComboTrackerProvider.CURRENT_COMBO).ifPresent(currentCombo -> {
                if (currentCombo.getCurrentCombo()==FIREBALL) {
                    player.sendSystemMessage(Component.literal("Casting Fireball").withStyle(ChatFormatting.RED),true);
                } else {
                    player.sendSystemMessage(Component.literal("Wrong Combo"));
                }
                currentCombo.clearThread();
            });

        });
        return true;
    }
}
