/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoRotations
extends Module {
    @Override
    public void onEnable() {
    }

    public NoRotations() {
        super("NoRotations", 0, Module.Category.PLAYER);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre() && ((EventPacket)event).getPacket() instanceof C03PacketPlayer.C05PacketPlayerLook) {
            event.setCancelled(true);
        }
    }
}

