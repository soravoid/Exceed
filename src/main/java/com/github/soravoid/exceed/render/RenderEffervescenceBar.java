package com.github.soravoid.exceed.render;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.capabilities.EffervescenceCapability;
import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderEffervescenceBar extends AbstractGui
{
    private static final int FADE_IN_TIME = 0;
    private static final int FULL_TIME = 120;
    private static final float FADE_MULTIPLIER = 0.1F;
    private static final float GONE_TIME = FULL_TIME + 3.141F / FADE_MULTIPLIER / 2;
    public static int timeSinceLastUpdate = 10000;
    private float transparency;

    @Nonnull
    private static final ResourceLocation location = new ResourceLocation(Exceed.MODID, "textures/gui/effervescence_bar.png");

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientTick(@Nonnull TickEvent.ClientTickEvent event) {
        if (!Minecraft.getInstance().isGamePaused() && event.phase == TickEvent.Phase.START){
            timeSinceLastUpdate++;
        }
    }

    @SubscribeEvent
    public void renderEffervescenceBar(RenderGameOverlayEvent.Post e)
    {
        if(e.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
        IEffervescence cap = Minecraft.getInstance().player.getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).orElse(null);
        if (cap == null) return;

        int xPos = 2;
        int yPos = 2;
        Minecraft.getInstance().getTextureManager().bindTexture(location);

        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.enableBlend();
        RenderSystem.color4f(1, 1, 1, transparency);

        float partialTicks = e.getPartialTicks();

        float v = timeSinceLastUpdate + partialTicks;
        float baseTransparency;
        if (v > GONE_TIME) {
            transparency = 0;
            return;
        } else if (v > FULL_TIME) {
            baseTransparency = MathHelper.cos((v - FULL_TIME) * FADE_MULTIPLIER);
        } else if (v < FADE_IN_TIME && transparency < 1) {
            baseTransparency = transparency + (1F / FADE_IN_TIME);
        } else {
            baseTransparency = 1;
        }

        transparency = baseTransparency;

        blit(xPos, yPos, 0, 0, 111, 18);
        int manabarwidth = (int)(((float) cap.getEffervescence() / cap.getMaxEffervescence()) * 96);
        blit(xPos + 7, yPos + 6, 0, 18, manabarwidth, 24);
        String s = "Effervescence " + cap.getEffervescence() + "/" + cap.getMaxEffervescence();
        yPos += 19;
        if(baseTransparency == 1)
        {
            Minecraft.getInstance().fontRenderer.drawString(s, xPos + 1, yPos, 0);
            Minecraft.getInstance().fontRenderer.drawString(s, xPos - 1, yPos, 0);
            Minecraft.getInstance().fontRenderer.drawString(s, xPos, yPos + 1, 0);
            Minecraft.getInstance().fontRenderer.drawString(s, xPos, yPos - 1, 0);
            Minecraft.getInstance().fontRenderer.drawString(s, xPos, yPos, 561872);
        }
    }
}
