/*
 * Decompiled with CFR 0.150.
 */
package Shadow.command.impl;

import Shadow.Client;
import Shadow.command.Command;
import Shadow.modules.Module;

public class Toggle
extends Command {
    public Toggle() {
        super("Toggle", "Toggles a module by a name.", "toggle <name>", "t");
    }

    @Override
    public void onCommand(String[] arrstring, String string) {
        if (arrstring.length > 0) {
            String string2 = arrstring[0];
            boolean bl = false;
            for (Module module : Client.modules) {
                if (!module.name.equalsIgnoreCase(string2)) continue;
                module.toggle();
                Client.addChatmessage(String.valueOf(new StringBuilder(String.valueOf(module.isEnabled() ? "Enabled" : "Disabled")).append(" ").append(module.name)));
                bl = true;
                break;
            }
            if (!bl) {
                Client.addChatmessage("Couldnt Find Module.");
            }
        }
    }
}

