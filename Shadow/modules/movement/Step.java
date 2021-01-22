/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Step
extends Module {
    public boolean boosted = false;
    int ticks = 0;

    public Step() {
        super("Step", 0, Module.Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (event.isPre()) {
                this.mc.thePlayer.cameraYaw = 0.1f;
            }
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

    @Override
    public void onDisable() {
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.timer.timerSpeed = 1.0f;
        this.mc.thePlayer.jumpMovementFactor = 0.02f;
        this.mc.thePlayer.speedInAir = 0.02f;
        this.boosted = false;
        this.ticks = 0;
        this.mc.thePlayer.stepHeight = 0.5f;
        this.mc.thePlayer.onGround = false;
    }

    @Override
    public void onEnable() {
        this.mc.thePlayer.onGround = false;
    }
}

