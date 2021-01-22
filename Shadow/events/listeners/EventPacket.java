/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;
import net.minecraft.network.Packet;

public class EventPacket
extends Event<EventPacket> {
    public Packet packet;

    public EventPacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}

