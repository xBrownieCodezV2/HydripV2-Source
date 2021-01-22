/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.BooleanSetting;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly
extends Module {
    boolean boosted = false;
    int ticks = 0;
    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "SmoothVanilla", "Freeze", "Glide", "Fake", "Redesky", "RedeskyBounce", "MCCentral", "Horizon / Verus", "Hypixel", "HypixelFast");
    public NumberSetting vanillaspeed;
    public BooleanSetting bobbing = new BooleanSetting("ViewBobbing", false);
    int delay = 0;

    public Fly() {
        super("Fly", 33, Module.Category.MOVEMENT);
        this.vanillaspeed = new NumberSetting("VanillaSpeed", 3.0, 1.0, 88.0, 1.0);
        this.addSettings(this.mode, this.bobbing, this.vanillaspeed);
    }

    @Override
    public void onEnable() {
        if (this.mode.is("Horizon / Verus")) {
            this.mc.thePlayer.motionY += 0.6;
        }
        this.mode.is("HypixelFast");
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.timer.timerSpeed = 1.0f;
        this.mc.thePlayer.jumpMovementFactor = 0.02f;
        this.mc.thePlayer.speedInAir = 0.02f;
        this.mc.timer.timerSpeed = 1.0f;
        this.mc.thePlayer.isDead = false;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            if (this.bobbing.isEnabled()) {
                this.mc.thePlayer.cameraYaw = 0.1f;
            }
            if (this.mode.is("Redesky")) {
                this.mc.thePlayer.capabilities.isFlying = false;
                this.mc.thePlayer.motionY = 0.0;
                if (this.mc.gameSettings.keyBindForward.pressed) {
                    this.mc.timer.timerSpeed = 1.0f;
                    this.mc.thePlayer.motionX = 0.0;
                    this.mc.thePlayer.motionZ = 0.0;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 7.0, this.mc.thePlayer.posY + 0.5, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 7.0, false));
                }
                if (this.mc.gameSettings.keyBindBack.pressed) {
                    this.mc.timer.timerSpeed = 1.0f;
                    this.mc.thePlayer.motionX = 0.0;
                    this.mc.thePlayer.motionZ = 0.0;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * -5.0, this.mc.thePlayer.posY + 0.7, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * -5.0, false));
                }
                if (this.mc.gameSettings.keyBindJump.pressed) {
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.timer.timerSpeed = 0.3f;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 10.0, this.mc.thePlayer.posZ, false));
                } else if (this.mc.gameSettings.keyBindSneak.pressed) {
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.timer.timerSpeed = 0.3f;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 10.0, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
                }
                this.mc.thePlayer.motionX = 0.0;
                this.mc.thePlayer.motionZ = 0.0;
            }
            if (this.mode.is("SmoothVanilla")) {
                this.mc.thePlayer.capabilities.isFlying = true;
            }
            if (this.mode.is("RedeskyBounce")) {
                int n = 0;
                this.mc.thePlayer.capabilities.isFlying = false;
                if (++n >= 20) {
                    this.mc.timer.timerSpeed = 0.6f;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.thePlayer.setPosition(this.mc.thePlayer.posX + this.mc.thePlayer.motionX * 4.0, this.mc.thePlayer.posY + 2.0, this.mc.thePlayer.posZ + this.mc.thePlayer.motionZ * 4.0);
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 7.0, this.mc.thePlayer.posY, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 7.0, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 10.0, this.mc.thePlayer.posZ, false));
                    n = 0;
                }
                if (n <= 20) {
                    this.mc.timer.timerSpeed = 1.0f;
                }
            }
            if (this.mode.is("Vanilla")) {
                this.mc.thePlayer.capabilities.isFlying = false;
                this.mc.thePlayer.motionY = 0.0;
                if (this.mc.gameSettings.keyBindJump.pressed) {
                    this.mc.thePlayer.motionY += this.vanillaspeed.getValue() / 10.0;
                }
                if (this.mc.gameSettings.keyBindSneak.pressed) {
                    this.mc.thePlayer.motionY -= this.vanillaspeed.getValue() / 10.0;
                }
                this.mc.thePlayer.setSpeed(this.vanillaspeed.getValue() / 10.0);
            }
            if (this.mode.is("MCCentral")) {
                this.mc.thePlayer.capabilities.isFlying = false;
                this.mc.thePlayer.motionY = 0.0;
                this.mc.thePlayer.onGround = true;
                this.mc.thePlayer.setSpeed(0.8);
            }
            if (this.mode.is("Fake")) {
                if (!this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.posY = this.mc.thePlayer.lastTickPosY;
                    this.mc.thePlayer.onGround = this.mc.thePlayer.onGround;
                }
                if (this.mc.thePlayer.onGround) {
                    this.mc.thePlayer.posY = this.mc.thePlayer.lastTickPosY;
                    this.mc.thePlayer.onGround = this.mc.thePlayer.onGround;
                }
            }
            if (this.mode.is("Redesky1")) {
                this.mc.thePlayer.cameraYaw = 0.1f;
                this.mc.thePlayer.capabilities.isFlying = false;
                this.mc.thePlayer.motionY = 0.0;
                if (this.mc.gameSettings.keyBindForward.pressed) {
                    this.mc.timer.timerSpeed = 1.0f;
                    this.mc.thePlayer.motionX = 0.0;
                    this.mc.thePlayer.motionZ = 0.0;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 7.0, this.mc.thePlayer.posY + 0.5, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 7.0, false));
                }
                if (this.mc.gameSettings.keyBindJump.pressed) {
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.timer.timerSpeed = 0.3f;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 11.0, this.mc.thePlayer.posZ, false));
                } else if (this.mc.gameSettings.keyBindSneak.pressed) {
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.timer.timerSpeed = 0.2f;
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 14.0, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 14.0, this.mc.thePlayer.posZ, false));
                }
                this.mc.thePlayer.motionX = 0.0;
                this.mc.thePlayer.motionZ = 0.0;
            }
        }
        if (this.mode.is("Glide") && (double)this.mc.thePlayer.fallDistance >= 1.1) {
            this.mc.thePlayer.motionY *= (double)0.9f;
            this.mc.thePlayer.onGround = true;
        }
        if (this.mode.is("Horizon / Verus")) {
            this.mc.thePlayer.setSprinting(true);
            this.mc.timer.timerSpeed = 1.0009499f;
            this.mc.thePlayer.onGround = true;
            this.mc.thePlayer.motionY = 0.0;
        }
        if (this.mode.is("Freeze")) {
            this.mc.thePlayer.isDead = true;
            this.mc.thePlayer.rotationYaw = this.mc.thePlayer.cameraYaw;
            this.mc.thePlayer.rotationPitch = this.mc.thePlayer.cameraPitch;
        }
    }
}

