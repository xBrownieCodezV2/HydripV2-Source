/*
 * Decompiled with CFR 0.150.
 */
package Shadow.altmanager;

public final class Alt {
    private String mask = "";
    private final String username;
    private String password;

    public Alt(String string, String string2) {
        this(string, string2, "");
    }

    public Alt(String string, String string2, String string3) {
        this.username = string;
        this.password = string2;
        this.mask = string3;
    }

    public void setPassword(String string) {
        this.password = string;
    }

    public void setMask(String string) {
        this.mask = string;
    }

    public String getMask() {
        return this.mask;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}

