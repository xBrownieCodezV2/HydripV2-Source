/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.combat;

import Shadow.events.Event;
import Shadow.events.listeners.EventMotion;
import Shadow.modules.Module;
import Shadow.util.Timer;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class TpAura
extends Module {
    int ticks = 0;
    public Timer timer = new Timer();
    int delay = 0;

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion && event.isPre()) {
            List list = this.mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
            list = list.stream().filter(entityLivingBase -> entityLivingBase.getDistanceToEntity(this.mc.thePlayer) < 7.0f && entityLivingBase != this.mc.thePlayer).collect(Collectors.toList());
            list.sort(Comparator.comparingDouble(entityLivingBase -> entityLivingBase.getDistanceToEntity(this.mc.thePlayer)));
            list = list.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
            this.mc.playerController.extendedReach();
            if (!list.isEmpty() && !this.mc.thePlayer.isDead) {
                ++this.ticks;
                ++this.delay;
                EntityLivingBase entityLivingBase2 = (EntityLivingBase)list.get(0);
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(entityLivingBase2.posX, entityLivingBase2.posY, entityLivingBase2.posZ, false));
                if (this.timer.hasTimedElapsed(55L, this.mc.thePlayer != null)) {
                    this.mc.thePlayer.swingItem();
                    if (this.mc.thePlayer != null) {
                        this.mc.playerController.attackEntity(this.mc.thePlayer, entityLivingBase2);
                    }
                }
                if (this.mc.thePlayer.isDead || this.mc.thePlayer == null) {
                    this.toggle();
                }
            } else if (list.isEmpty()) {
                this.delay = 0;
            }
        }
    }

    @Override
    public void onDisable() {
        this.ticks = 0;
        this.mc.gameSettings.keyBindUseItem.pressed = false;
        this.delay = 0;
    }

    public TpAura() {
        super("TpAura", 211, Module.Category.COMBAT);
    }

    public float[] getRotations(Entity entity) {
        double d = entity.posX + (entity.posX - entity.lastTickPosX) - this.mc.thePlayer.posX;
        double d2 = entity.posY - 3.5 + (double)entity.getEyeHeight() - this.mc.thePlayer.posY + (double)this.mc.thePlayer.getEyeHeight();
        double d3 = entity.posZ + (entity.posZ - entity.lastTickPosZ) - this.mc.thePlayer.posZ;
        double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        } else if (d > 0.0 && d3 < 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        return new float[]{f, f2};
    }
}

