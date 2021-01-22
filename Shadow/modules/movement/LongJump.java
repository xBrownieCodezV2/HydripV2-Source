/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class LongJump
extends Module {
    @Override
    public void onDisable() {
        this.mc.thePlayer.setSprinting(false);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            this.mc.thePlayer.cameraYaw = 0.1f;
            if (this.mc.thePlayer.onGround) {
                this.mc.thePlayer.jump();
            }
            this.mc.thePlayer.jumpMovementFactor = 0.18f;
            this.mc.thePlayer.motionY += (double)0.07f;
            this.mc.thePlayer.jumpMovementFactor = 0.17f;
            if (this.mc.thePlayer.fallDistance > 2.0f) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 5.0, this.mc.thePlayer.posY + 1.0, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 5.0, false));
                this.mc.thePlayer.motionY = 1.4f;
            }
        }
    }

    public LongJump() {
        super("LongJump", 34, Module.Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
    }
}

