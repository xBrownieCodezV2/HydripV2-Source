/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules;

import Shadow.events.Event;
import Shadow.settings.KeybindSetting;
import Shadow.settings.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;

public class Module {
    public KeybindSetting keyCode = new KeybindSetting(0);
    public List<Setting> settings;
    public String name;
    public int index;
    public boolean expanded;
    public boolean toggled;
    public Minecraft mc = Minecraft.getMinecraft();
    public Category category;

    public Module(String string, int n, Category category) {
        this.settings = new ArrayList<Setting>();
        this.name = string;
        this.keyCode.code = n;
        this.category = category;
    }

    public void onEvent(Event event) {
    }

    public boolean isEnabled() {
        return this.toggled;
    }

    public int getKey() {
        return this.keyCode.code;
    }

    public void onDisable() {
    }

    public void addSettings(Setting ... arrsetting) {
        this.settings.addAll(Arrays.asList(arrsetting));
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void onEnable() {
    }

    public static enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        PLAYER("Player"),
        MISC("Misc");

        public String name;
        public int moduleIndex;

        private Category(String string2) {
            this.name = string2;
        }
    }
}

