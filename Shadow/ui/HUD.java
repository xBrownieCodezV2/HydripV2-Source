/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package Shadow.ui;

import Shadow.Client;
import Shadow.events.listeners.EventRenderGUI;
import Shadow.modules.Module;
import Shadow.ui.Notifications;
import Shadow.util.Rainbow;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUD {
    public Minecraft mc = Minecraft.getMinecraft();

    public void draw() {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        FontRenderer fontRenderer = this.mc.fontRendererObj;
        Notifications.Notify();
        Client.modules.sort(Comparator.comparingInt(object -> this.mc.fontRendererObj.getStringWidth(((Module)object).name)).reversed());
        GlStateManager.pushMatrix();
        GlStateManager.translate(4.0f, 4.0f, 0.0f);
        GlStateManager.scale(2.0f, 2.0f, 1.0f);
        GlStateManager.translate(-4.0f, -4.0f, 0.0f);
        fontRenderer.func_175065_a(Client.name, 3.0f, 3.0f, Rainbow.getRainbow(4.0f, 0.9f, 1.0f, 150L), true);
        GlStateManager.popMatrix();
        fontRenderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_AQUA).append("FPS").append((Object)ChatFormatting.GRAY).append(": ").append((Object)ChatFormatting.WHITE).append(Minecraft.getDebugFPS())), 6.0, 21.0, -1);
        GlStateManager.pushMatrix();
        GlStateManager.translate(4.0f, 4.0f, 0.0f);
        GlStateManager.scale(1.3, 1.3, 1.0);
        GlStateManager.translate(-4.0f, -4.0f, 0.0f);
        fontRenderer.func_175065_a("Redesky edition", 55.0f, 3.0f, 0xA9A9A9, true);
        GlStateManager.popMatrix();
        int n = 0;
        for (Module module : Client.modules) {
            if (!module.toggled || module.name.equals("TabGui")) continue;
            double d = n * (fontRenderer.FONT_HEIGHT + 5);
            Gui.drawRect(scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.name) - 11, d, scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.name) - 9, (double)(6 + fontRenderer.FONT_HEIGHT) + d, 2);
            fontRenderer.func_175065_a(module.name, scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module.name) - 4, (float)(4.0 + d), Rainbow.getRainbow(4.0f, 1.0f, 1.0f, n * 150), true);
            ++n;
        }
        Client.onEvent(new EventRenderGUI());
    }
}

