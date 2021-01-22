/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events;

import Shadow.events.EventDirection;
import Shadow.events.EventType;
import net.minecraft.network.Packet;

public class Event<T> {
    public EventDirection direction;
    public boolean cancelled;
    public EventType type;
    private Packet packet;

    public void setCancelled(boolean bl) {
        this.cancelled = bl;
    }

    public void setType(EventType eventType) {
        this.type = eventType;
    }

    public boolean isIncoming() {
        if (this.direction == null) {
            return false;
        }
        return this.direction == EventDirection.INCOMING;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public EventDirection getDirection() {
        return this.direction;
    }

    public EventType getType() {
        return this.type;
    }

    public void setDirection(EventDirection eventDirection) {
        this.direction = eventDirection;
    }

    public boolean isOutgoing() {
        if (this.direction == null) {
            return false;
        }
        return this.direction == EventDirection.OUTGOING;
    }

    public boolean isPre() {
        if (this.type == null) {
            return false;
        }
        return this.type == EventType.PRE;
    }

    public boolean isPost() {
        if (this.type == null) {
            return false;
        }
        return this.type == EventType.POST;
    }
}

