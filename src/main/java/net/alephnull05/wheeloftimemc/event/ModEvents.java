package net.alephnull05.wheeloftimemc.event;

import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.alephnull05.wheeloftimemc.weaving.ComboTracker;
import net.alephnull05.wheeloftimemc.weaving.ComboTrackerProvider;
import net.alephnull05.wheeloftimemc.weaving.PlayerMagic;
import net.alephnull05.wheeloftimemc.weaving.PlayerMagicProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WheelOfTimeMC.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerMagicProvider.PLAYER_MAGIC).isPresent()) {
                event.addCapability(new ResourceLocation(WheelOfTimeMC.MOD_ID, "properties"), new PlayerMagicProvider());
            }
            if(!event.getObject().getCapability(ComboTrackerProvider.CURRENT_COMBO).isPresent()) {
                event.addCapability(new ResourceLocation(WheelOfTimeMC.MOD_ID, "properties"), new ComboTrackerProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(ComboTrackerProvider.CURRENT_COMBO).ifPresent(oldStore -> {
                event.getOriginal().getCapability(ComboTrackerProvider.CURRENT_COMBO).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMagic.class);
        event.register(ComboTracker.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(magic -> {
                if(magic.getMagicLevel() < 100 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    magic.addMagic(10);
                    if(magic.getMagicLevel()==100) {
                        event.player.sendSystemMessage(Component.literal("Magic Full"));
                    }
                }
            });
        }
    }
}
