package net.alephnull05.wheeloftimemc.networking.packet;

import net.alephnull05.wheeloftimemc.weaving.PlayerMagicProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();

            player.sendSystemMessage(Component.literal("Test"));
            EntityType.COW.spawn(level,null,player, player.blockPosition(), MobSpawnType.SPAWN_EGG, true, false);
            player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(magic -> {
                magic.subMagic(1);
                player.sendSystemMessage(Component.literal("Magic Level: " + magic.getMagicLevel()));
            });
        });
        return true;
    }
}
