package net.alephnull05.wheeloftimemc.event;

import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.alephnull05.wheeloftimemc.networking.ModMessages;
import net.alephnull05.wheeloftimemc.networking.packet.*;
import net.alephnull05.wheeloftimemc.util.KeyBinding;
import net.alephnull05.wheeloftimemc.weaving.ComboTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = WheelOfTimeMC.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            //weave fire
            if(KeyBinding.FIRE_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingFireC2SPacket());
            }
            //weave earth
            if(KeyBinding.EARTH_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingEarthC2SPacket());
            }
            //weave air
            if(KeyBinding.AIR_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingAirC2SPacket());
            }
            //weave water
            if(KeyBinding.WATER_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingWaterC2SPacket());
            }
            //weave spirit
            if(KeyBinding.SPIRIT_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingSpiritC2SPacket());
            }
            //use weave
            if (KeyBinding.WEAVE_KEY.consumeClick()) {
                ModMessages.sendToServer(new WeavingC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = WheelOfTimeMC.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.FIRE_KEY);
            event.register(KeyBinding.EARTH_KEY);
            event.register(KeyBinding.AIR_KEY);
            event.register(KeyBinding.WATER_KEY);
            event.register(KeyBinding.SPIRIT_KEY);
            event.register(KeyBinding.WEAVE_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        }
    }
}
