/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.movement;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.BooleanSetting;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FlyV2
extends Module {
    public boolean boosted = false;
    public int ticks = 0;
    public NumberSetting vanillaspeed;
    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Redesky", "RedeskyBounce", "Redesky3", "Hypixel", "HypixelWtf", "SmoothVanilla", "Cubecraft", "Verus", "Spartan", "Packet", "MCCentral", "Fake", "ACR", "NCP");
    public int delay = 0;
    public BooleanSetting bobbing = new BooleanSetting("ViewBobbing", false);

    public FlyV2() {
        super("FlyV2", 0, Module.Category.MOVEMENT);
        this.vanillaspeed = new NumberSetting("VanillaSpeed", 3.0, 1.0, 10.0, 0.1);
        this.addSettings(this.mode, this.bobbing, this.vanillaspeed);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (event.isPre()) {
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
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX + this.mc.thePlayer.getLookVec().xCoord * 7.0, this.mc.thePlayer.posY + 0.7, this.mc.thePlayer.posZ + this.mc.thePlayer.getLookVec().zCoord * 7.0, false));
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
                        this.mc.timer.timerSpeed = 0.6f;
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 10.0, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 10.0, this.mc.thePlayer.posZ, false));
                    } else if (this.mc.gameSettings.keyBindSneak.pressed) {
                        this.mc.thePlayer.motionY = 0.0;
                        this.mc.timer.timerSpeed = 0.6f;
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
                    this.mc.timer.timerSpeed = 1.0f;
                    if (this.mc.gameSettings.keyBindJump.pressed) {
                        this.mc.thePlayer.motionY += this.vanillaspeed.getValue();
                    }
                    if (this.mc.gameSettings.keyBindSneak.pressed) {
                        this.mc.thePlayer.motionY -= this.vanillaspeed.getValue();
                    }
                    this.mc.thePlayer.setSpeed(this.vanillaspeed.getValue());
                }
                this.mode.is("MCCentral");
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
                if (this.mode.is("ACR")) {
                    ++this.ticks;
                    this.boosted = this.mc.thePlayer.hurtTime > 0;
                    if (this.boosted && !this.mc.thePlayer.isCollidedHorizontally) {
                        this.ticks = 0;
                        this.mc.thePlayer.setPosition(this.mc.thePlayer.posX + this.mc.thePlayer.motionX * 10.0, this.mc.thePlayer.posY - this.mc.thePlayer.motionY, this.mc.thePlayer.posZ + this.mc.thePlayer.motionZ * 10.0);
                    }
                    if (this.mc.thePlayer.hurtTime < 1) {
                        // empty if block
                    }
                }
                if (this.mode.is("Hypixel")) {
                    this.mc.timer.timerSpeed = 1.0f;
                    this.mc.thePlayer.capabilities.isFlying = false;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.thePlayer.onGround = true;
                    this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.lastTickPosY, this.mc.thePlayer.posZ);
                    if (this.mc.thePlayer.ticksExisted % 3 == 0) {
                        double d = this.mc.thePlayer.posY - 1.0E-10;
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, d, this.mc.thePlayer.posZ, true));
                    }
                    double d = this.mc.thePlayer.posY + 1.0E-10;
                    this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, d, this.mc.thePlayer.posZ);
                }
                this.mode.is("HypixelWtf");
                this.mode.is("NCP");
                if (this.mode.is("Redesky3")) {
                    this.mc.thePlayer.capabilities.isFlying = false;
                    this.mc.thePlayer.motionY = 0.0;
                    this.mc.timer.timerSpeed = 0.2f;
                    if (this.mc.gameSettings.keyBindJump.pressed) {
                        this.mc.thePlayer.motionY += this.vanillaspeed.getValue();
                    }
                    if (this.mc.gameSettings.keyBindSneak.pressed) {
                        this.mc.thePlayer.motionY -= this.vanillaspeed.getValue();
                    }
                    this.mc.thePlayer.setSpeed(this.vanillaspeed.getValue());
                    this.mc.thePlayer.setPosition(this.mc.thePlayer.posX + this.mc.thePlayer.motionX, this.mc.thePlayer.posY + this.mc.thePlayer.motionY, this.mc.thePlayer.posZ + this.mc.thePlayer.motionZ);
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
                }
            }
        } else if (event instanceof EventPacket && this.mode.is("Redesky3") && ((EventPacket)event).getPacket() instanceof C03PacketPlayer) {
            this.mc.thePlayer.posY = this.mc.thePlayer.lastTickPosY;
            this.mc.thePlayer.posX = this.mc.thePlayer.lastTickPosX;
            this.mc.thePlayer.posZ = this.mc.thePlayer.lastTickPosZ;
        }
    }

    @Override
    public void onDisable() {
        if (this.mode.is("Redesky3")) {
            this.mc.thePlayer.swingItem();
        }
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.timer.timerSpeed = 1.0f;
        this.mc.thePlayer.jumpMovementFactor = 0.02f;
        this.mc.thePlayer.speedInAir = 0.02f;
        this.delay = 0;
        this.ticks = 0;
        this.boosted = false;
    }

    @Override
    public void onEnable() {
        if (this.mode.is("HypixelWtf") && this.mc.thePlayer.onGround) {
            this.mc.thePlayer.jump();
            double d = this.mc.thePlayer.posY + 4.0;
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, d, this.mc.thePlayer.posZ, false));
        }
        if (this.mode.is("Redesky3")) {
            this.mc.thePlayer.swingItem();
        }
    }
}

