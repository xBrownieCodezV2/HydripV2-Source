/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Ninja
extends Module {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre()) {
            List list = this.mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
            list = list.stream().filter(entityLivingBase -> entityLivingBase.getDistanceToEntity(this.mc.thePlayer) < 4.0f && entityLivingBase != this.mc.thePlayer).collect(Collectors.toList());
            list.sort(Comparator.comparingDouble(entityLivingBase -> entityLivingBase.getDistanceToEntity(this.mc.thePlayer)));
            list = list.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
            this.mc.playerController.extendedReach();
            if (!list.isEmpty() && !this.mc.thePlayer.isDead) {
                EntityLivingBase entityLivingBase2 = (EntityLivingBase)list.get(0);
                this.mc.thePlayer.setPosition(entityLivingBase2.posX, entityLivingBase2.posY, entityLivingBase2.posZ);
                if (this.mc.thePlayer.isDead || this.mc.thePlayer == null || this.mc.theWorld == null) {
                    this.toggle();
                }
            }
        }
    }

    public Ninja() {
        super("Ninja", 47, Module.Category.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}

