/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.render;

import Shadow.events.Event;
import Shadow.events.listeners.EventMotion;
import Shadow.modules.Module;
import Shadow.util.Timer;

public class HeadRotations
extends Module {
    public Timer timer = new Timer();

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion && event.isPre() && (this.mc.gameSettings.thirdPersonView == 1 || this.mc.gameSettings.thirdPersonView == 2)) {
            this.mc.thePlayer.rotationYawHead = ((EventMotion)event).getYaw();
            this.mc.thePlayer.renderYawOffset = ((EventMotion)event).getYaw();
        }
    }

    public HeadRotations() {
        super("HeadRotations", 0, Module.Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}

