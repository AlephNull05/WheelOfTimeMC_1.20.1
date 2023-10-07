package net.alephnull05.wheeloftimemc.networking;

import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.alephnull05.wheeloftimemc.networking.packet.WeavingAirC2SPacket;
import net.alephnull05.wheeloftimemc.networking.packet.WeavingEarthC2SPacket;
import net.alephnull05.wheeloftimemc.networking.packet.WeavingFireC2SPacket;
import net.alephnull05.wheeloftimemc.networking.packet.WeavingWaterC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(WheelOfTimeMC.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;
        net.messageBuilder(WeavingFireC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeavingFireC2SPacket::new)
                .encoder(WeavingFireC2SPacket::toBytes)
                .consumerMainThread(WeavingFireC2SPacket::handle)
                .add();
        net.messageBuilder(WeavingEarthC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeavingEarthC2SPacket::new)
                .encoder(WeavingEarthC2SPacket::toBytes)
                .consumerMainThread(WeavingEarthC2SPacket::handle)
                .add();
        net.messageBuilder(WeavingWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeavingWaterC2SPacket::new)
                .encoder(WeavingWaterC2SPacket::toBytes)
                .consumerMainThread(WeavingWaterC2SPacket::handle)
                .add();
        net.messageBuilder(WeavingAirC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeavingAirC2SPacket::new)
                .encoder(WeavingAirC2SPacket::toBytes)
                .consumerMainThread(WeavingAirC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void   sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
