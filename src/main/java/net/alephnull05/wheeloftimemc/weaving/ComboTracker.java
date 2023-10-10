package net.alephnull05.wheeloftimemc.weaving;

import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComboTracker {
    private List<Integer> currentCombo = new ArrayList<>();

    public List<Integer> getCurrentCombo() {
        return currentCombo;
    }

    public void addThread(int thread) {
        this.currentCombo.add(thread);

    }

    public void clearThread() {
        this.currentCombo.clear();
    }

    public boolean checkCombo(List<Integer> combo, int[] targetCombo) {
        // Check if the combo list matches the target combo
        if (combo.size() != targetCombo.length) {
            return false;
        }

        for (int i = 0; i < combo.size(); i++) {
            if (combo.get(i) != targetCombo[i]) {
                return false;
            }
        }

        return true;
    }


    public void copyFrom(ComboTracker source) {
        this.currentCombo = source.currentCombo;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putIntArray("combo", currentCombo);
    }

    public void loadNBTData(CompoundTag nbt) {
        currentCombo = Arrays.stream(nbt.getIntArray("combo")).boxed().collect(Collectors.toList());
    }

}
