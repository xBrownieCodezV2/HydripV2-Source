/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class RedeJump
extends Module {
    @Override
    public void onEnable() {
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 7.0, this.mc.thePlayer.posY + 1.5, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 7.0, true));
        this.mc.thePlayer.setPosition(this.mc.thePlayer.posX + this.mc.thePlayer.motionX * 3.0, this.mc.thePlayer.posY, this.mc.thePlayer.posZ + this.mc.thePlayer.motionZ * 3.0);
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 10.0, this.mc.thePlayer.posZ, false));
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
        this.mc.timer.timerSpeed = 1.0f;
    }

    public RedeJump() {
        super("RedeJump", 0, Module.Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            if (this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
                this.mc.timer.timerSpeed = 0.1f;
            }
            this.toggle();
        }
    }
}

