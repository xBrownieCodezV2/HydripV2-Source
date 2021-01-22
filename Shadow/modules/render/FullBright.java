/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.render;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;

public class FullBright
extends Module {
    public FullBright() {
        super("FullBright", 35, Module.Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.mc.gameSettings.gammaSetting = 100.0f;
    }

    @Override
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = 0.0f;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            event.isPre();
        }
    }
}

