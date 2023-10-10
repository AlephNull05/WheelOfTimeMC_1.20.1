package net.alephnull05.wheeloftimemc.weaving;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComboTrackerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ComboTracker> CURRENT_COMBO = CapabilityManager.get(new CapabilityToken<ComboTracker>() {});

    private ComboTracker currentCombo = null;
    private final LazyOptional<ComboTracker> optional = LazyOptional.of(this::createComboTracker);

    private ComboTracker createComboTracker() {
        if(this.currentCombo == null) {
            this.currentCombo = new ComboTracker();
        }

        return this.currentCombo;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CURRENT_COMBO) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createComboTracker().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createComboTracker().loadNBTData(nbt);
    }
}
