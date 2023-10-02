package net.alephnull05.wheeloftimemc.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY_WHEELOFTIME = "key.category.wheeloftimemc.wheeloftime";
    public static final String KEY_FIRE = "key.wheeloftimemc.fire";
    public static final String KEY_EARTH = "key.wheeloftimemc.earth";
    public static final String KEY_AIR = "key.wheeloftimemc.air";
    public static final String KEY_WATER = "key.wheeloftimemc.water";
    public static final String KEY_SPIRIT = "key.wheeloftimemc.spirit";

    public static final KeyMapping FIRE_KEY = new KeyMapping(KEY_FIRE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, KEY_CATEGORY_WHEELOFTIME);
    public static final KeyMapping EARTH_KEY = new KeyMapping(KEY_EARTH, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_2, KEY_CATEGORY_WHEELOFTIME);
    public static final KeyMapping AIR_KEY = new KeyMapping(KEY_AIR, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_6, KEY_CATEGORY_WHEELOFTIME);
    public static final KeyMapping WATER_KEY = new KeyMapping(KEY_WATER, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_4, KEY_CATEGORY_WHEELOFTIME);
    public static final KeyMapping SPIRIT_KEY = new KeyMapping(KEY_SPIRIT, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_5, KEY_CATEGORY_WHEELOFTIME);

}
