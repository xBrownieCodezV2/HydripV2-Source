/*
 * Decompiled with CFR 0.150.
 */
package Shadow.command;

import Shadow.Client;
import Shadow.command.Command;
import Shadow.command.impl.Bind;
import Shadow.command.impl.Info;
import Shadow.command.impl.Toggle;
import Shadow.events.listeners.EventChat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    public String prefix = ".";
    public List<Command> commands = new ArrayList<Command>();

    public void handleChat(EventChat eventChat) {
        String string = eventChat.getMessage();
        if (!string.startsWith(this.prefix)) {
            return;
        }
        eventChat.setCancelled(true);
        string = string.substring(this.prefix.length());
        boolean bl = false;
        if (string.split(" ").length > 0) {
            String string2 = string.split(" ")[0];
            for (Command command : this.commands) {
                if (!command.aliases.contains(string2) && !command.name.equalsIgnoreCase(string2)) continue;
                command.onCommand(Arrays.copyOfRange(string.split(" "), 1, string.split(" ").length), string);
                bl = true;
                break;
            }
        }
        if (!bl) {
            Client.addChatmessage("Couldnt find command.");
        }
    }

    public void setup() {
        this.commands.add(new Toggle());
        this.commands.add(new Bind());
        this.commands.add(new Info());
    }

    public CommandManager() {
        this.setup();
    }
}

