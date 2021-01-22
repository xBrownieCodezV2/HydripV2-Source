/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventMotion;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.BooleanSetting;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import Shadow.util.Timer;

public class Speed1
extends Module {
    public Timer timer = new Timer();
    public BooleanSetting autojump;
    public BooleanSetting lowhop;
    public BooleanSetting ongroundspoof;
    public ModeSetting mode = new ModeSetting("Mode", "Custom", "Custom", "Jump", "Hypixel", "NCPYPort", "AACLowhop", "NCP", "NCPFast", "Redesky", "Verus");
    public NumberSetting customspeed = new NumberSetting("CustomSpeed", 0.2, 0.1, 2.0, 1.0);
    public NumberSetting lowhopmodifier;

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
        this.mc.timer.timerSpeed = 1.0f;
        this.mc.thePlayer.speedInAir = 0.02f;
    }

    public Speed1() {
        super("Speed1", 0, Module.Category.MOVEMENT);
        this.autojump = new BooleanSetting("CustomAutojump", true);
        this.ongroundspoof = new BooleanSetting("ClientSidedOnGroundSpoof", false);
        this.lowhop = new BooleanSetting("CustomLowhop", false);
        this.lowhopmodifier = new NumberSetting("CustomLowhopY", 0.1, 0.0, 0.5, 1.0);
        this.addSettings(this.mode, this.customspeed, this.autojump, this.ongroundspoof, this.lowhop, this.lowhopmodifier);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion) {
            event.isPre();
        } else if (event instanceof EventUpdate && event.isPre()) {
            double d;
            double d2;
            double d3;
            if (this.ongroundspoof.isEnabled() && !this.mc.thePlayer.onGround) {
                this.mc.thePlayer.posY = this.mc.thePlayer.lastTickPosY;
            }
            if (this.mode.is("Custom")) {
                if (this.autojump.isEnabled()) {
                    this.mc.gameSettings.keyBindJump.pressed = false;
                    if (this.mc.thePlayer.onGround && this.mc.thePlayer.isMoving()) {
                        this.mc.thePlayer.jump();
                        if (this.lowhop.isEnabled()) {
                            this.mc.thePlayer.motionY -= this.lowhopmodifier.getValue();
                        }
                    }
                }
                this.mc.thePlayer.setSpeed(this.customspeed.getValue());
            }
            if (this.mode.is("Jump")) {
                this.mc.gameSettings.keyBindJump.pressed = false;
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.jump();
                }
            }
            if (this.mode.is("Hypixel") && this.mc.thePlayer != null && this.mc.theWorld != null && this.mc.thePlayer.isMoving() && !this.mc.thePlayer.isCollidedHorizontally) {
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.jump();
                    this.mc.timer.timerSpeed = 1.05f;
                    this.mc.thePlayer.motionX *= (double)1.07f;
                    this.mc.thePlayer.motionZ *= (double)1.07f;
                } else {
                    this.mc.thePlayer.jumpMovementFactor = 0.0265f;
                    this.mc.timer.timerSpeed = 1.2f;
                    d3 = this.mc.thePlayer.getDirection();
                    d2 = 1.0;
                    d = Math.sqrt(this.mc.thePlayer.motionX * this.mc.thePlayer.motionX + this.mc.thePlayer.motionZ * this.mc.thePlayer.motionZ);
                    this.mc.thePlayer.motionX = -Math.sin(d3) * d2 * d;
                    this.mc.thePlayer.motionZ = Math.cos(d3) * d2 * d;
                }
            }
            if (this.mode.is("NCP") && this.mc.thePlayer != null && this.mc.theWorld != null && this.mc.thePlayer.isMoving() && !this.mc.thePlayer.isCollidedHorizontally) {
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.jump();
                    this.mc.timer.timerSpeed = 1.05f;
                    this.mc.thePlayer.motionX *= (double)1.07f;
                    this.mc.thePlayer.motionZ *= (double)1.07f;
                } else {
                    this.mc.thePlayer.jumpMovementFactor = 0.0265f;
                    this.mc.timer.timerSpeed = 1.0f;
                    d3 = this.mc.thePlayer.getDirection();
                    d2 = 1.0;
                    d = Math.sqrt(this.mc.thePlayer.motionX * this.mc.thePlayer.motionX + this.mc.thePlayer.motionZ * this.mc.thePlayer.motionZ);
                    this.mc.thePlayer.motionX = -Math.sin(d3) * d2 * d;
                    this.mc.thePlayer.motionZ = Math.cos(d3) * d2 * d;
                }
            }
            if (this.mode.is("Redesky") && this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
                this.mc.thePlayer.motionX *= (double)1.6f;
                this.mc.thePlayer.motionZ *= (double)1.6f;
            }
            if (this.mode.is("NCPYPort")) {
                this.mc.thePlayer.isMoving();
            }
        }
    }
}

