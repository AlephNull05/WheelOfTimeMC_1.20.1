package net.alephnull05.wheeloftimemc.item;

import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.alephnull05.wheeloftimemc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WheelOfTimeMC.MOD_ID);

    public static final RegistryObject<CreativeModeTab> Wheel_Of_Time_Materials = CREATIVE_MODE_TABS.register("wheel_of_time_materials",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CUENDILLAR_SHARD.get()))
                    .title(Component.translatable("creativetab.wheel_of_time_materials"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.CUENDILLAR_SHARD.get());
                        pOutput.accept(ModItems.TIN_INGOT.get());
                        pOutput.accept(ModItems.RAW_TIN.get());

                        pOutput.accept(ModBlocks.CUENDILLAR_BLOCK.get());
                        pOutput.accept(ModBlocks.TIN_ORE.get());
                        pOutput.accept(ModBlocks.TIN_BLOCK.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
