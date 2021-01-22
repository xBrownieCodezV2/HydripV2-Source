/*
 * Decompiled with CFR 0.150.
 */
package Shadow.util;

import java.awt.Color;

public class Rainbow {
    public static int getRainbow(float f, float f2, float f3, long l) {
        float f4 = (float)((System.currentTimeMillis() + l) % (long)((int)(f * 1000.0f))) / (f * 1000.0f);
        int n = Color.HSBtoRGB(f4, f2, f3);
        return n;
    }
}

