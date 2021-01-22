/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastUse
extends Module {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre() && this.mc.thePlayer.onGround && this.mc.thePlayer.getItemInUseDuration() >= 15 && this.mc.thePlayer.getItemInUse().getItem() instanceof ItemFood) {
            for (int i = 0; i <= 20; ++i) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }

    public FastUse() {
        super("Fastuse", 0, Module.Category.PLAYER);
    }

    @Override
    public void onDisable() {
    }
}

