/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.misc;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;

public class Disabler
extends Module {
    @Override
    public void onDisable() {
        this.mc.timer.timerSpeed = 1.0f;
    }

    public Disabler() {
        super("Disabler", 0, Module.Category.MISC);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre() && ((EventPacket)event).getPacket() instanceof C0FPacketConfirmTransaction) {
            event.setCancelled(true);
        }
        if (event instanceof EventPacket && event.isPre() && ((EventPacket)event).getPacket() instanceof C00PacketKeepAlive) {
            event.setCancelled(true);
        }
        if (event instanceof EventPacket && event.isPre() && ((EventPacket)event).getPacket() instanceof C0BPacketEntityAction) {
            event.setCancelled(true);
        }
    }
}

