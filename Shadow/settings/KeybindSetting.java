/*
 * Decompiled with CFR 0.150.
 */
package Shadow.settings;

import Shadow.settings.Setting;

public class KeybindSetting
extends Setting {
    public int code;

    public int getKeyCode() {
        return this.code;
    }

    public KeybindSetting(int n) {
        this.name = "Bind";
        this.code = n;
    }

    public void setKeyCode(int n) {
        this.code = n;
    }
}

