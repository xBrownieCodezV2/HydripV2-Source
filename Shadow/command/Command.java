/*
 * Decompiled with CFR 0.150.
 */
package Shadow.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
    public String name;
    public String syntax;
    public String descripton;
    public List<String> aliases = new ArrayList<String>();

    public String getDescripton() {
        return this.descripton;
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setSyntax(String string) {
        this.syntax = string;
    }

    public String getName() {
        return this.name;
    }

    public void setAliases(List<String> list) {
        this.aliases = list;
    }

    public Command(String string, String string2, String string3, String ... arrstring) {
        this.name = string;
        this.descripton = string2;
        this.syntax = string3;
        this.aliases = Arrays.asList(arrstring);
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public String getSyntax() {
        return this.syntax;
    }

    public void setDescripton(String string) {
        this.descripton = string;
    }

    public abstract void onCommand(String[] var1, String var2);
}

