/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;

public class WallClimb
extends Module {
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
        this.mc.thePlayer.stepHeight = 0.4f;
    }

    public WallClimb() {
        super("WallClimb", 0, Module.Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mc.thePlayer.isCollidedHorizontally) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.onGround = true;
        }
    }
}

