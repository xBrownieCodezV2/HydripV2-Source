/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.combat;

import Shadow.events.Event;
import Shadow.events.listeners.EventMotion;
import Shadow.modules.Module;
import Shadow.modules.render.HeadRotations;
import Shadow.settings.BooleanSetting;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import Shadow.util.Timer;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Killaura
extends Module {
    public ModeSetting autoblockmode;
    public NumberSetting blockrange;
    public NumberSetting range;
    public NumberSetting turnspeed;
    public BooleanSetting raycast;
    public Timer timer = new Timer();
    int delay = 0;
    public BooleanSetting autoblock;
    public NumberSetting aps;
    int ticks = 0;
    public HeadRotations headrotations;
    public ModeSetting sortmode;
    public ModeSetting mode;
    public ModeSetting rotationmode;
    public static boolean shouldRotate = false;
    public NumberSetting autoblockdelay;
    public ModeSetting target1;

    public float[] getSmoothRotations(Entity entity) {
        double d = entity.posX - entity.prevPosX - (this.mc.thePlayer.posX - this.mc.thePlayer.prevPosX);
        double d2 = entity.posY - entity.prevPosY - (this.mc.thePlayer.posY - this.mc.thePlayer.prevPosY);
        double d3 = entity.posZ - entity.prevPosZ - (this.mc.thePlayer.posZ - this.mc.thePlayer.prevPosZ);
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

    @Override
    public void onDisable() {
        this.ticks = 0;
        this.mc.gameSettings.keyBindUseItem.pressed = false;
        this.delay = 0;
        shouldRotate = false;
    }

    public Killaura() {
        super("Killaura", 19, Module.Category.COMBAT);
        this.range = new NumberSetting("Range", 4.0, 1.0, 12.0, 1.0);
        this.autoblock = new BooleanSetting("Autoblock", false);
        this.target1 = new ModeSetting("Target", "Players", "Players", "Animals", "Mobs", "Villagers");
        this.autoblockmode = new ModeSetting("BlockMode", "NCP", "Hydrip", "Hydrip2", "NCP");
        this.raycast = new BooleanSetting("Raycast", false);
        this.blockrange = new NumberSetting("BlockRange", 4.0, 1.0, 15.0, 1.0);
        this.rotationmode = new ModeSetting("RotationMode", "NoRotations", "NoRotations", "NCP", "Basic");
        this.aps = new NumberSetting("APS", 11.0, 1.0, 20.0, 1.0);
        this.autoblockdelay = new NumberSetting("AutoblockDelay", 75.0, 0.0, 150.0, 1.0);
        this.sortmode = new ModeSetting("SortMode", "Distance", "Distance", "Health", "Angle");
        this.mode = new ModeSetting("Mode", "Switch", "Single", "Switch", "Multi");
        this.turnspeed = new NumberSetting("Turnspeed", 40.0, 0.0, 180.0, 1.0);
        this.headrotations = new HeadRotations();
        this.addSettings(this.mode, this.range, this.autoblock, this.raycast, this.blockrange, this.rotationmode, this.aps, this.autoblockmode, this.target1, this.sortmode, this.turnspeed);
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

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion && event.isPre()) {
            List list = this.mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
            list = list.stream().filter(entityLivingBase -> (double)entityLivingBase.getDistanceToEntity(this.mc.thePlayer) < this.range.getValue() && entityLivingBase != this.mc.thePlayer).collect(Collectors.toList());
            if (this.mode.is("Switch")) {
                if (this.sortmode.is("Distance")) {
                    list.sort(Comparator.comparingDouble(entityLivingBase -> entityLivingBase.getDistanceToEntity(this.mc.thePlayer)));
                }
                if (this.sortmode.is("Health")) {
                    list.sort(Comparator.comparingDouble(entityLivingBase -> entityLivingBase.getHealth()));
                }
                if (this.sortmode.is("Angle")) {
                    list.sort(Comparator.comparingDouble(entityLivingBase -> this.getRotations((Entity)entityLivingBase)[0]));
                }
            }
            if (this.target1.is("Players")) {
                list = list.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
            }
            if (this.target1.is("Mobs")) {
                list = list.stream().filter(EntityMob.class::isInstance).collect(Collectors.toList());
            }
            if (this.target1.is("Animals")) {
                list = list.stream().filter(EntityAnimal.class::isInstance).collect(Collectors.toList());
            }
            if (this.target1.is("Villagers")) {
                list = list.stream().filter(EntityVillager.class::isInstance).collect(Collectors.toList());
            }
            if (!list.isEmpty() && !this.mc.thePlayer.isDead) {
                shouldRotate = true;
                EntityLivingBase entityLivingBase2 = (EntityLivingBase)list.get(0);
                ++this.ticks;
                ++this.delay;
                if (this.rotationmode.is("NCP")) {
                    ((EventMotion)event).setYaw(this.getRotations(entityLivingBase2)[0]);
                    ((EventMotion)event).setPitch(this.getRotations(entityLivingBase2)[1]);
                }
                if (this.mc.gameSettings.thirdPersonView == 1 || this.mc.gameSettings.thirdPersonView == 2 && this.headrotations.isEnabled()) {
                    this.mc.thePlayer.rotationYawHead = ((EventMotion)event).getYaw();
                    this.mc.thePlayer.renderYawOffset = ((EventMotion)event).getYaw();
                }
                try {
                    if (this.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword) {
                        if (this.autoblockmode.is("NCP") && this.autoblock.isEnabled() && (double)entityLivingBase2.getDistanceToEntity(this.mc.thePlayer) < this.blockrange.getValue()) {
                            this.mc.playerController.sendUseItem(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem());
                            if (this.mc.thePlayer.isBlocking() && this.timer.hasTimedElapsed(75L, false)) {
                                this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                            }
                        }
                        if (this.autoblockmode.is("Hydrip") && this.autoblock.isEnabled() && (double)entityLivingBase2.getDistanceToEntity(this.mc.thePlayer) < this.blockrange.getValue()) {
                            this.mc.playerController.sendUseItem(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem());
                            if (this.mc.thePlayer.isBlocking() && this.timer.hasTimedElapsed(90L, false)) {
                                this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                            }
                        }
                        if (this.autoblockmode.is("Hydrip2") && this.autoblock.isEnabled() && (double)entityLivingBase2.getDistanceToEntity(this.mc.thePlayer) < this.blockrange.getValue()) {
                            this.mc.playerController.sendUseItem(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem());
                            if (this.mc.thePlayer.isBlocking() && this.timer.hasTimedElapsed(0L, false)) {
                                this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                            }
                        }
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (this.timer.hasTimedElapsed((long)(1000.0 / this.aps.getValue()), true) && this.mc.thePlayer != null) {
                    if (this.raycast.isEnabled() && ((EventMotion)event).yaw == this.getRotations(entityLivingBase2)[0] && ((EventMotion)event).pitch == this.getRotations(entityLivingBase2)[1]) {
                        this.mc.thePlayer.swingItem();
                        this.mc.thePlayer.onCriticalHit(entityLivingBase2);
                        if (this.mc.thePlayer != null) {
                            this.mc.playerController.attackEntity(this.mc.thePlayer, entityLivingBase2);
                        }
                        if (this.mc.thePlayer.isDead || this.mc.thePlayer == null) {
                            this.toggle();
                        } else if (list.isEmpty()) {
                            this.delay = 0;
                        }
                    } else if (!this.raycast.isEnabled()) {
                        this.mc.thePlayer.swingItem();
                        this.mc.thePlayer.onCriticalHit(entityLivingBase2);
                        if (this.mc.thePlayer != null) {
                            this.mc.playerController.attackEntity(this.mc.thePlayer, entityLivingBase2);
                        }
                        if (this.mc.thePlayer.isDead || this.mc.thePlayer == null) {
                            this.toggle();
                        } else if (list.isEmpty()) {
                            this.delay = 0;
                        }
                    }
                }
            }
        }
    }
}

