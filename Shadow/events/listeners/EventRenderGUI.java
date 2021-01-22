/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;
import net.minecraft.client.gui.Gui;

public class EventRenderGUI
extends Event<EventRenderGUI> {
    public void draw() {
        Gui.drawRect(50.0, 50.0, 50.0, 50.0, -1);
    }
}

