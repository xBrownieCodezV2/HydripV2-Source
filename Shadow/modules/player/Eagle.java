/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class Eagle
extends Module {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mc.thePlayer != null && this.mc.theWorld != null) {
            ItemStack itemStack = this.mc.thePlayer.getCurrentEquippedItem();
            BlockPos blockPos = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 0.5, this.mc.thePlayer.posZ);
            if (itemStack != null && itemStack.getItem() instanceof ItemBlock) {
                this.mc.gameSettings.keyBindSneak.pressed = false;
                if (this.mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.air) {
                    this.mc.gameSettings.keyBindSneak.pressed = true;
                }
            }
        }
    }

    public Eagle() {
        super("Eagle", 0, Module.Category.MISC);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}

