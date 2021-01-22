/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventPacket;
import Shadow.modules.Module;
import net.minecraft.init.Blocks;

public class Fastice
extends Module {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventPacket && event.isPre()) {
            Blocks.ice.slipperiness = 0.4f;
            Blocks.packed_ice.slipperiness = 0.4f;
        }
    }

    public Fastice() {
        super("Fastice", 0, Module.Category.PLAYER);
    }

    @Override
    public void onDisable() {
        Blocks.ice.slipperiness = 0.98f;
        Blocks.packed_ice.slipperiness = 0.98f;
    }
}

