/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.misc;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.ModeSetting;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;

public class AntiBot
extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Watchdog", "Watchdog", "Advanced");

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    public AntiBot() {
        super("AntiBot", 0, Module.Category.MISC);
        this.addSettings(this.mode);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mode.is("Watchdog")) {
            for (Object e : this.mc.theWorld.loadedEntityList) {
                if (!((Entity)e).isInvisible() || (Entity)e == this.mc.thePlayer) continue;
                this.mc.theWorld.removeEntity((Entity)e);
            }
        }
        if (event instanceof EventPacket && this.mode.is("Advanced")) {
            for (Object e : this.mc.theWorld.loadedEntityList) {
                if (!(((EventPacket)event).getPacket() instanceof S0CPacketSpawnPlayer)) continue;
                double d = this.mc.thePlayer.posX;
                double d2 = this.mc.thePlayer.posY;
                double d3 = this.mc.thePlayer.posZ;
                double d4 = d - ((Entity)e).posX;
                d4 = d2 - ((Entity)e).posY;
                d4 = d3 - ((Entity)e).posZ;
                if (!(d <= 17.0) || !(d3 <= 17.0) || !(d2 <= 17.0) || (Entity)e == this.mc.thePlayer) continue;
                event.setCancelled(true);
            }
        }
    }
}

