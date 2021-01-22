/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;

public class InvMove
extends Module {
    @Override
    public void onDisable() {
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindForward) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindForward.pressed = false;
        }
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindBack) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindBack.pressed = false;
        }
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindRight) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindRight.pressed = false;
        }
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindLeft) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindLeft.pressed = false;
        }
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindJump) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindJump.pressed = false;
        }
        if (!GameSettings.isKeyDown(this.mc.gameSettings.keyBindSprint) || this.mc.currentScreen != null) {
            this.mc.gameSettings.keyBindSprint.pressed = false;
        }
    }

    @Override
    public void onEnable() {
    }

    public InvMove() {
        super("InvMove", 0, Module.Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre() && this.mc.currentScreen instanceof GuiContainer) {
            this.mc.gameSettings.keyBindForward.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindForward);
            this.mc.gameSettings.keyBindBack.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindBack);
            this.mc.gameSettings.keyBindRight.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindRight);
            this.mc.gameSettings.keyBindLeft.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindLeft);
            this.mc.gameSettings.keyBindJump.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindJump);
            this.mc.gameSettings.keyBindSprint.pressed = GameSettings.isKeyDown(this.mc.gameSettings.keyBindSprint);
        }
    }
}

