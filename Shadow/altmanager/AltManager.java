/*
 * Decompiled with CFR 0.150.
 */
package Shadow.altmanager;

import Shadow.altmanager.Alt;
import java.util.ArrayList;

public class AltManager {
    public static ArrayList<Alt> registry = new ArrayList();
    public static Alt lastAlt;

    public void setLastAlt(Alt alt) {
        lastAlt = alt;
    }

    public ArrayList<Alt> getRegistry() {
        return registry;
    }
}

