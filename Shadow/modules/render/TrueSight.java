/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.render;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import net.minecraft.entity.Entity;

public class TrueSight
extends Module {
    public TrueSight() {
        super("TrueSight", 0, Module.Category.RENDER);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            for (Object e : this.mc.theWorld.loadedEntityList) {
                ((Entity)e).setInvisible(false);
            }
        }
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}

