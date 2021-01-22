/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package Shadow;

import Shadow.command.CommandManager;
import Shadow.events.Event;
import Shadow.events.listeners.EventChat;
import Shadow.events.listeners.EventKey;
import Shadow.modules.Module;
import Shadow.modules.combat.Killaura;
import Shadow.modules.combat.TpAura;
import Shadow.modules.combat.Velocity;
import Shadow.modules.combat.WTap;
import Shadow.modules.misc.AntiBot;
import Shadow.modules.misc.Blink;
import Shadow.modules.misc.Breaker;
import Shadow.modules.misc.Disabler;
import Shadow.modules.movement.ComuFly;
import Shadow.modules.movement.Fly;
import Shadow.modules.movement.FlyV2;
import Shadow.modules.movement.HighJump;
import Shadow.modules.movement.LongJump;
import Shadow.modules.movement.RedeJump;
import Shadow.modules.movement.Speed;
import Shadow.modules.movement.Speed1;
import Shadow.modules.movement.Sprint;
import Shadow.modules.movement.Step;
import Shadow.modules.movement.WallClimb;
import Shadow.modules.player.Cheststealer;
import Shadow.modules.player.Eagle;
import Shadow.modules.player.FastUse;
import Shadow.modules.player.Fastice;
import Shadow.modules.player.InvClean;
import Shadow.modules.player.InvMove;
import Shadow.modules.player.Jesus;
import Shadow.modules.player.Ninja;
import Shadow.modules.player.NoFall;
import Shadow.modules.player.NoRotations;
import Shadow.modules.player.Phase;
import Shadow.modules.player.Scaffold;
import Shadow.modules.render.FullBright;
import Shadow.modules.render.TabGui;
import Shadow.modules.render.TrueSight;
import Shadow.ui.HUD;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.opengl.Display;

public class Client {
    public static CommandManager commandManager;
    public static CopyOnWriteArrayList<Module> modules;
    public static HUD hud;
    public static String name;
    public static String version;

    public static void keyPress(int n) {
        Client.onEvent(new EventKey(n));
        for (Module module : modules) {
            if (module.getKey() != n) continue;
            module.toggle();
        }
    }

    public static List<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : modules) {
            if (module.category != category) continue;
            arrayList.add(module);
        }
        return arrayList;
    }

    public static void onEvent(Event event) {
        if (event instanceof EventChat) {
            commandManager.handleChat((EventChat)event);
        }
        for (Module module : modules) {
            if (!module.toggled) continue;
            module.onEvent(event);
        }
    }

    public static void addChatmessage(String string) {
        string = String.valueOf(new StringBuilder("\u00a79").append(name).append("\u00a77: ").append(string));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(string));
    }

    public static void startUp() {
        System.out.println(String.valueOf(new StringBuilder("Starting ").append(name).append(" ").append(version)));
        Display.setTitle((String)String.valueOf(new StringBuilder(String.valueOf(name)).append(" v").append(version).append(" (Beta)")));
        modules.add(new Fly());
        modules.add(new HighJump());
        modules.add(new Sprint());
        modules.add(new Killaura());
        modules.add(new NoFall());
        modules.add(new TabGui());
        modules.add(new Eagle());
        modules.add(new FastUse());
        modules.add(new InvClean());
        modules.add(new FullBright());
        modules.add(new RedeJump());
        modules.add(new Cheststealer());
        modules.add(new Speed());
        modules.add(new AntiBot());
        modules.add(new Blink());
        modules.add(new WallClimb());
        modules.add(new TrueSight());
        modules.add(new NoRotations());
        modules.add(new Ninja());
        modules.add(new Phase());
        modules.add(new WTap());
        modules.add(new Scaffold());
        modules.add(new Step());
        modules.add(new Disabler());
        modules.add(new Velocity());
        modules.add(new InvMove());
        modules.add(new Fastice());
        modules.add(new Jesus());
        modules.add(new LongJump());
        modules.add(new ComuFly());
        modules.add(new TpAura());
        modules.add(new FlyV2());
        modules.add(new Breaker());
        modules.add(new Speed1());
    }

    static {
        name = "Hydrip";
        version = "2.1";
        modules = new CopyOnWriteArrayList();
        hud = new HUD();
        commandManager = new CommandManager();
    }
}

