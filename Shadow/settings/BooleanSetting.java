/*
 * Decompiled with CFR 0.150.
 */
package Shadow.settings;

import Shadow.settings.Setting;

public class BooleanSetting
extends Setting {
    public boolean enabled;

    public BooleanSetting(String string, boolean bl) {
        this.name = string;
        this.enabled = bl;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean bl) {
        this.enabled = bl;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}

