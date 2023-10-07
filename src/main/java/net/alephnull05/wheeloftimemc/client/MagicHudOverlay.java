package net.alephnull05.wheeloftimemc.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alephnull05.wheeloftimemc.WheelOfTimeMC;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class MagicHudOverlay {
    private static final ResourceLocation WEAVE_FIRE = new ResourceLocation(WheelOfTimeMC.MOD_ID,
            "textures/weaving/WeaveFire.png");
    private static final ResourceLocation WEAVE_EARTH = new ResourceLocation(WheelOfTimeMC.MOD_ID,
            "textures/weaving/WeaveEarth.png");
    private static final ResourceLocation WEAVE_WATER = new ResourceLocation(WheelOfTimeMC.MOD_ID,
            "textures/weaving/WeaveWater.png");
    private static final ResourceLocation WEAVE_AIR = new ResourceLocation(WheelOfTimeMC.MOD_ID,
            "textures/weaving/WeaveAir.png");

    private static final IGuiOverlay HUD_MAGIC = ((gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, WEAVE_FIRE);

        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x - 94 + (i * 9), y - 54,0,0,12,12,
                    12,12);
        }

    });
}
