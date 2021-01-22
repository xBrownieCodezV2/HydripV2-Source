/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class EventLiquidBB
extends Event<EventLiquidBB> {
    AxisAlignedBB axisAlignedBB;
    BlockPos pos;
    BlockLiquid blockLiquid;

    public void setAxisAlignedBB(AxisAlignedBB axisAlignedBB) {
        this.axisAlignedBB = axisAlignedBB;
    }

    public EventLiquidBB(BlockLiquid blockLiquid, BlockPos blockPos, AxisAlignedBB axisAlignedBB) {
        this.blockLiquid = blockLiquid;
        this.pos = blockPos;
        this.axisAlignedBB = axisAlignedBB;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public BlockLiquid getBlock() {
        return this.blockLiquid;
    }

    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }
}

