/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;

public class EventKey
extends Event<EventKey> {
    public int code;

    public void setCode(int n) {
        this.code = n;
    }

    public EventKey(int n) {
        this.code = n;
    }

    public int getCode() {
        return this.code;
    }
}

