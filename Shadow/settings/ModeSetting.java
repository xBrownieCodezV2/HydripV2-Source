/*
 * Decompiled with CFR 0.150.
 */
package Shadow.settings;

import Shadow.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class ModeSetting
extends Setting {
    public int index;
    public List<String> modes;

    public String getMode() {
        return this.modes.get(this.index);
    }

    public boolean is(String string) {
        return this.index == this.modes.indexOf(string);
    }

    public ModeSetting(String string, String string2, String ... arrstring) {
        this.name = string;
        this.modes = Arrays.asList(arrstring);
        this.index = this.modes.indexOf(string2);
    }

    public void cycle() {
        this.index = this.index < this.modes.size() - 1 ? ++this.index : 0;
    }
}

