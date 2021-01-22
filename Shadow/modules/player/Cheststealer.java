/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.inventory.ContainerChest;

public class Cheststealer
extends Module {
    int ticks = 0;

    public Cheststealer() {
        super("Cheststealer", 44, Module.Category.PLAYER);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            ++this.ticks;
            if (this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
                ContainerChest containerChest = (ContainerChest)this.mc.thePlayer.openContainer;
                for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
                    if (containerChest.getLowerChestInventory().getStackInSlot(i) == null || this.ticks < 2) continue;
                    this.mc.playerController.windowClick(containerChest.windowId, i, 0, 1, this.mc.thePlayer);
                    this.ticks = 0;
                }
                containerChest.getInventory().isEmpty();
            }
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}

