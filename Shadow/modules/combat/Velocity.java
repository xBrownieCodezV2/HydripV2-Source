/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.combat;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.ModeSetting;

public class Velocity
extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "OldAGC", "MotionReduce", "OldAGC", "AAC");

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mode.is("OldAGC") && !this.mc.thePlayer.onGround && this.mc.thePlayer.hurtTime > 0) {
            this.mc.thePlayer.onGround = true;
            this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.lastTickPosY, this.mc.thePlayer.posZ);
        }
        if (this.mode.is("MotionReduce") && !this.mc.thePlayer.onGround && this.mc.thePlayer.hurtTime > 0) {
            this.mc.thePlayer.motionX *= (double)1.012f;
            this.mc.thePlayer.motionY *= (double)1.002f;
        }
    }

    public Velocity() {
        super("Velocity", 0, Module.Category.COMBAT);
        this.addSettings(this.mode);
    }
}

