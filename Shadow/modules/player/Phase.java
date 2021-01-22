/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.util.Timer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldSettings;

public class Phase
extends Module {
    public Timer timer = new Timer();

    public Phase() {
        super("Phase", 0, Module.Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (event.isPre()) {
                this.mc.thePlayer.setGameType(WorldSettings.GameType.CREATIVE);
                this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            } else {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(this.mc.thePlayer.getCurrentEquippedItem()));
                if (this.timer.hasTimedElapsed(30000L, false)) {
                    this.timer.reset();
                }
            }
        }
    }

    @Override
    public void onDisable() {
    }
}

