/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class ComuFly
extends Module {
    public Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onEnable() {
        this.mc.thePlayer.motionY = 1.8;
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
    }

    public ComuFly() {
        super("ComuFly", 0, Module.Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            this.mc.timer.timerSpeed = 0.8f;
            this.mc.gameSettings.fovSetting = 130.0f;
            this.mc.thePlayer.cameraYaw = 0.12f;
            if (this.mc.thePlayer.ticksExisted % 3 == 0) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
                this.mc.timer.timerSpeed = 0.3f;
                this.mc.thePlayer.motionY = 0.03;
            }
        }
    }

    @Override
    public void onDisable() {
        this.mc.gameSettings.fovSetting = 120.0f;
        this.mc.timer.timerSpeed = 1.0f;
    }
}

