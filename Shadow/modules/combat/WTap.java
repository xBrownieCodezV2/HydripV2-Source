/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.combat;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;

public class WTap
extends Module {
    @Override
    public void onEnable() {
    }

    public WTap() {
        super("WTap", 0, Module.Category.COMBAT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && !this.mc.thePlayer.isSprinting() && this.mc.thePlayer.hurtTime > 1) {
            this.mc.thePlayer.setSprinting(true);
        }
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
    }
}

