/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.misc;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Blink
extends Module {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre() && ((EventPacket)event).getPacket() instanceof C03PacketPlayer) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onEnable() {
    }

    public Blink() {
        super("Blink", 38, Module.Category.MISC);
    }

    @Override
    public void onDisable() {
    }
}

