/*
 * Decompiled with CFR 0.150.
 */
package Shadow.command.impl;

import Shadow.Client;
import Shadow.command.Command;

public class Info
extends Command {
    public Info() {
        super("Info", "Tells about client information.", "info", "i");
    }

    @Override
    public void onCommand(String[] arrstring, String string) {
        Client.addChatmessage(String.valueOf(new StringBuilder("Client Made By Plexter and Redstone, Version v").append(Client.version).append(".")));
    }
}

