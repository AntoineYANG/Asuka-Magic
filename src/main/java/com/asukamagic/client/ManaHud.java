package com.asukamagic.client;

import com.asukamagic.AsukaMagic;
import com.asukamagic.mana.ManaData;
import com.asukamagic.mana.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = AsukaMagic.MOD_ID, value = Dist.CLIENT)
public final class ManaHud {

    public static final Identifier LAYER_ID = Identifier.fromNamespaceAndPath(AsukaMagic.MOD_ID, "mana_hud");

    @SubscribeEvent
    public static void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR, LAYER_ID, ManaHud::render);
    }

    private static void render(GuiGraphics g, net.minecraft.client.DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (mc.options.hideGui) return;

        ManaData mana = mc.player.getData(ModAttachments.MANA);

        int max = Math.max(1, mana.maxMana());
        int cur = Math.max(0, Math.min(mana.mana(), max));

        int sw = mc.getWindow().getGuiScaledWidth();
        int sh = mc.getWindow().getGuiScaledHeight();

        int barW = 81;
        int barH = 8;

        int x = (sw - barW) / 2;
        int y = sh - 49;

        int filled = (int) Math.floor((cur / (double) max) * barW);

        int bg = 0x90000000;
        int fg = 0x9000A8FF;

        g.fill(x, y, x + barW, y + barH, bg);
        if (filled > 0) g.fill(x, y, x + filled, y + barH, fg);

        Font font = mc.font;
        Component text = Component.translatable("hud." + AsukaMagic.MOD_ID + ".mana", cur, max);

        int tx = x + (barW - font.width(text)) / 2;
        int ty = y - 10;
        g.drawString(font, text, tx, ty, 0xFFFFFF, true);
    }

}
