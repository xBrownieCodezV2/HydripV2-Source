/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;

public class HighJump
extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Bypass");
    public NumberSetting height = new NumberSetting("Height", 1.0, 1.0, 3.0, 1.0);

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mode.is("Vanilla")) {
            this.mc.thePlayer.onGround = true;
        }
        if (this.mode.is("Bypass") && this.mc.thePlayer.fallDistance >= 5.0f) {
            this.mc.timer.timerSpeed = 0.4f;
        }
    }

    @Override
    public void onDisable() {
        this.mc.timer.timerSpeed = 1.0f;
    }

    public HighJump() {
        super("HighJump", 0, Module.Category.MOVEMENT);
        this.addSettings(this.mode, this.height);
    }
}

