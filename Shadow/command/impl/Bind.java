/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package Shadow.command.impl;

import Shadow.Client;
import Shadow.command.Command;
import Shadow.modules.Module;
import org.lwjgl.input.Keyboard;

public class Bind
extends Command {
    public Bind() {
        super("Bind", "Binds a module by a name.", "bind <name> <key> | clear", "b");
    }

    @Override
    public void onCommand(String[] arrstring, String string) {
        if (arrstring.length == 2) {
            String object = arrstring[0];
            String string2 = arrstring[1];
            boolean bl = false;
            for (Module module : Client.modules) {
                if (!module.name.equalsIgnoreCase(object)) continue;
                module.keyCode.setKeyCode(Keyboard.getKeyIndex((String)string2.toUpperCase()));
                Client.addChatmessage(String.format("Bound %s to %s", module.name, Keyboard.getKeyName((int)module.getKey())));
                bl = true;
                break;
            }
            if (!bl) {
                Client.addChatmessage("Couldnt find module.");
            }
        }
        if (arrstring.length == 1) {
            if (arrstring[0].equalsIgnoreCase("clear")) {
                for (Module module : Client.modules) {
                    module.keyCode.setKeyCode(0);
                }
            }
            Client.addChatmessage("Cleared all binds.");
        }
    }
}

