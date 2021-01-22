/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.BooleanSetting;

public class Sprint
extends Module {
    public BooleanSetting Mode = new BooleanSetting("Omni", false);

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            if (!this.Mode.isEnabled()) {
                if (this.mc.gameSettings.keyBindForward.pressed && !this.mc.thePlayer.isUsingItem() && !this.mc.thePlayer.isCollidedHorizontally) {
                    this.mc.thePlayer.setSprinting(true);
                }
            } else {
                this.mc.thePlayer.setSprinting(true);
            }
        }
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
    }

    @Override
    public void onEnable() {
    }

    public Sprint() {
        super("Sprint", 0, Module.Category.MOVEMENT);
        this.addSettings(this.Mode);
    }
}

