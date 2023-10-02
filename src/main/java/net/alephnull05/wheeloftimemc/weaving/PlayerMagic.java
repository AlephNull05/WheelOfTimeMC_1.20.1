package net.alephnull05.wheeloftimemc.weaving;

import net.minecraft.nbt.CompoundTag;

public class PlayerMagic {
    private int magic;
    private int minMagic = 0;
    private int maxMagic = 100;

    public int getMagicLevel() {
        return magic;
    }

    public void addMagic(int add) {
        this.magic = Math.min(magic+add, maxMagic);
    }

    public void subMagic(int sub) {
        this.magic = Math.max(magic-sub, minMagic);
    }

    public void copyFrom(PlayerMagic source) {
        this.magic = source.magic;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("magicLevel", magic);
    }

    public void loadNBTData(CompoundTag nbt) {
        magic = nbt.getInt("magicLevel");
    }

}
