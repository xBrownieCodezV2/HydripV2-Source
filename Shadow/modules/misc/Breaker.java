/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.misc;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Breaker
extends Module {
    public NumberSetting range;
    public ModeSetting Choose = new ModeSetting("Beds", "Beds", "Beds", "Cakes", "Eggs");

    public Breaker() {
        super("Breaker", 0, Module.Category.MISC);
        this.range = new NumberSetting("range", 1.0, 1.0, 6.0, 1.0);
        this.addSettings(this.Choose, this.range);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            int n = (int)this.range.getValue();
            for (int i = -n; i < n; ++i) {
                for (int j = n; j > -n; --j) {
                    for (int k = -n; k < n; ++k) {
                        int n2 = (int)this.mc.thePlayer.posX + i;
                        int n3 = (int)this.mc.thePlayer.posY + j;
                        int n4 = (int)this.mc.thePlayer.posZ + k;
                        BlockPos blockPos = new BlockPos(n2, n3, n4);
                        Block block = this.mc.theWorld.getBlockState(blockPos).getBlock();
                        if (this.Choose.is("Beds") && block instanceof BlockBed) {
                            this.mc.thePlayer.swingItem();
                            this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                            this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                        }
                        if (this.Choose.is("Cakes") && block instanceof BlockCake) {
                            this.mc.thePlayer.swingItem();
                            this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                            this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                        }
                        if (!this.Choose.is("Eggs") || !(block instanceof BlockDragonEgg)) continue;
                        this.mc.thePlayer.swingItem();
                        this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                        this.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                    }
                }
            }
        }
    }
}

