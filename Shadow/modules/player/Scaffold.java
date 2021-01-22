/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventMotion;
import Shadow.modules.Module;
import Shadow.modules.render.HeadRotations;
import Shadow.util.BlockUtil;
import Shadow.util.Timer;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class Scaffold
extends Module {
    public Timer timer = new Timer();
    private BlockPos currentPos;
    public static boolean Safewalk;
    private EnumFacing currentFacing;
    int ticks = 0;
    private boolean rotated = false;
    public HeadRotations headrotations = new HeadRotations();

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMotion && event.isPre()) {
            this.rotated = false;
            this.currentPos = null;
            this.currentFacing = null;
            BlockPos blockPos = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 1.0, this.mc.thePlayer.posZ);
            new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 2.0, this.mc.thePlayer.posZ);
            if (this.mc.theWorld.getBlockState(blockPos).getBlock() instanceof BlockAir) {
                this.setBlockAndFacing(blockPos);
                if (this.currentPos != null) {
                    float[] arrf = BlockUtil.getDirectionToBlock(this.currentPos.getX(), this.currentPos.getY(), this.currentPos.getZ(), this.currentFacing);
                    float f = arrf[0];
                    float f2 = Math.min(90.0f, arrf[1] + 9.0f);
                    this.rotated = true;
                    ((EventMotion)event).setYaw(f);
                    ((EventMotion)event).setPitch(f2);
                    if (this.timer.hasTimedElapsed(0L, false) && this.mc.thePlayer.getCurrentEquippedItem() != null && this.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock && this.mc.playerController.func_178890_a(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.getCurrentEquippedItem(), this.currentPos, this.currentFacing, new Vec3(this.currentPos.getX(), this.currentPos.getY(), this.currentPos.getZ()))) {
                        this.timer.reset();
                        this.mc.thePlayer.swingItem();
                        Scaffold.Safewalk();
                    }
                }
            }
            if (this.mc.gameSettings.thirdPersonView == 1 || this.mc.gameSettings.thirdPersonView == 2 && this.headrotations.isEnabled()) {
                this.mc.thePlayer.rotationYawHead = ((EventMotion)event).getYaw();
                this.mc.thePlayer.renderYawOffset = ((EventMotion)event).getYaw();
            }
        }
    }

    private void setBlockAndFacing2(BlockPos blockPos) {
        if (this.mc.theWorld.getBlockState(blockPos.add(0, -2, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, -2, 0);
            this.currentFacing = EnumFacing.UP;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(-1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(-1, 0, 0);
            this.currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(1, 0, 0);
            this.currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(0, 0, -1)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, 0, -1);
            this.currentFacing = EnumFacing.SOUTH;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(0, 0, 1)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, 0, 1);
            this.currentFacing = EnumFacing.NORTH;
        } else {
            this.currentPos = null;
            this.currentFacing = null;
        }
    }

    public static void Safewalk() {
        Safewalk = true;
    }

    private void setBlockAndFacing(BlockPos blockPos) {
        if (this.mc.theWorld.getBlockState(blockPos.add(0, -1, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, -1, 0);
            this.currentFacing = EnumFacing.UP;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(-1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(-1, 0, 0);
            this.currentFacing = EnumFacing.EAST;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(1, 0, 0);
            this.currentFacing = EnumFacing.WEST;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(0, 0, -1)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, 0, -1);
            this.currentFacing = EnumFacing.SOUTH;
        } else if (this.mc.theWorld.getBlockState(blockPos.add(0, 0, 1)).getBlock() != Blocks.air) {
            this.currentPos = blockPos.add(0, 0, 1);
            this.currentFacing = EnumFacing.NORTH;
        } else {
            this.currentPos = null;
            this.currentFacing = null;
        }
    }

    public Scaffold() {
        super("Scaffold", 46, Module.Category.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.ticks = 0;
        this.mc.timer.timerSpeed = 1.0f;
        Safewalk = false;
    }
}

