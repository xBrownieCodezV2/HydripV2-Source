/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;

public class Speed
extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "NCPhop", "ACRdamage", "Redesky", "Yport");
    public NumberSetting vanillaspeed = new NumberSetting("VanillaSpeed", 2.0, 2.0, 8.0, 1.0);

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
        this.mc.timer.timerSpeed = 1.0f;
    }

    public Speed() {
        super("Speed", 45, Module.Category.MOVEMENT);
        this.addSettings(this.mode, this.vanillaspeed);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            if (this.mode.is("Vanilla")) {
                this.mc.thePlayer.setSpeed(this.vanillaspeed.getValue() / 10.0);
            }
            if (this.mode.is("NCPhop")) {
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.jump();
                }
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.motionY *= (double)0.99f;
                this.mc.timer.timerSpeed = 1.09499f;
            }
            if (this.mode.is("ACRdamage") && this.mc.thePlayer.hurtTime >= 1 && this.mc.thePlayer.onGround) {
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.capabilities.isFlying = false;
                this.mc.thePlayer.motionY = 0.01049f;
                this.mc.thePlayer.onGround = true;
                this.mc.thePlayer.setSpeed(0.9f);
            }
            this.mode.is("Redesky");
            if (this.mode.is("Yport") && this.mc.thePlayer.onGround) {
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.jump();
                this.mc.timer.timerSpeed = 1.002f;
                this.mc.thePlayer.motionY *= 0.6;
                this.mc.thePlayer.motionX *= 1.3;
                this.mc.thePlayer.motionZ *= 1.3;
                this.mc.thePlayer.jumpMovementFactor = 2.0f;
                this.mc.thePlayer.moveStrafing = 1.8f;
            }
        }
    }
}

