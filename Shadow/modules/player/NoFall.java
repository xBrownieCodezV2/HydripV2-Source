/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import Shadow.util.Timer;

public class NoFall
extends Module {
    public Timer timer = new Timer();

    @Override
    public void onDisable() {
    }

    public NoFall() {
        super("NoFall", 0, Module.Category.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre() && this.timer.hasTimedElapsed(8L, false)) {
            if (!this.mc.thePlayer.onGround) {
                this.mc.thePlayer.onGround = true;
            }
            this.timer.reset();
        }
    }
}

