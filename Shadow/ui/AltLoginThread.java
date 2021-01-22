/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 */
package Shadow.ui;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

public final class AltLoginThread
extends Thread {
    private Minecraft mc = Minecraft.getMinecraft();
    private String status;
    private final String password;
    private final String username;

    public void setStatus(String string) {
        this.status = string;
    }

    public String getStatus() {
        return this.status;
    }

    public AltLoginThread(String string, String string2) {
        super("Alt Login Thread");
        this.username = string;
        this.password = string2;
        this.status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GRAY).append("Waiting..."));
    }

    private Session createSession(String string, String string2) {
        YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(string);
        yggdrasilUserAuthentication.setPassword(string2);
        try {
            yggdrasilUserAuthentication.logIn();
            return new Session(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang");
        }
        catch (AuthenticationException authenticationException) {
            authenticationException.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        if (this.password.equals("")) {
            this.mc.session = new Session(this.username, "", "", "mojang");
            this.status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GREEN).append("Logged in. (").append(this.username).append(" - offline name)"));
            return;
        }
        this.status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.YELLOW).append("Logging in..."));
        Session session = this.createSession(this.username, this.password);
        if (session == null) {
            this.status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.RED).append("Login failed!"));
        } else {
            this.status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GREEN).append("Logged in. (").append(session.getUsername()).append(")"));
            this.mc.session = session;
        }
    }
}

