/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.network.status.client.C01PacketPing;

public class Jesus
extends Module {
    public void onSendPacket(EventPacket eventPacket) {
        if (eventPacket.packet instanceof C01PacketPing) {
            C01PacketPing c01PacketPing = (C01PacketPing)eventPacket.packet;
            c01PacketPing.clientTime = 0L;
            eventPacket.packet = c01PacketPing;
        }
    }

    @Override
    public void onEvent(Event event) {
    }

    public Jesus() {
        super("Jesus", 0, Module.Category.MOVEMENT);
    }
}

