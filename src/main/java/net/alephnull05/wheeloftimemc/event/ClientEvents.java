package net.alephnull05.wheeloftimemc.event;

import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.alephnull05.wheeloftimemc.networking.ModMessages;
import net.alephnull05.wheeloftimemc.networking.packet.ExampleC2SPacket;
import net.alephnull05.wheeloftimemc.networking.packet.WeavingFireC2SPacket;
import net.alephnull05.wheeloftimemc.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
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
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Weaving Earth"));
            }
            //weave air
            if(KeyBinding.AIR_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Weaving Air"));
            }
            //weave water
            if(KeyBinding.WATER_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Weaving Water"));
            }
            //weave spirit
            if(KeyBinding.SPIRIT_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Weaving Spirit"));
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
        }
    }
}
