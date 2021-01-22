/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;

public class EventChat
extends Event<EventChat> {
    public String message;

    public EventChat(String string) {
        this.message = string;
    }

    public void setMessage(String string) {
        this.message = string;
    }

    public String getMessage() {
        return this.message;
    }
}

