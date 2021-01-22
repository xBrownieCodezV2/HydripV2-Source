/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 *  org.lwjgl.input.Keyboard
 */
package Shadow.altmanager;

import Shadow.altmanager.Alt;
import Shadow.altmanager.AltManager;
import Shadow.altmanager.GuiAltManager;
import Shadow.altmanager.PasswordField;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.io.IOException;
import java.net.Proxy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public class GuiAddAlt
extends GuiScreen {
    private final GuiAltManager manager;
    private String status = String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GRAY).append("Idle..."));
    private PasswordField password;
    private GuiTextField username;

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        switch (guiButton.id) {
            case 0: {
                AddAltThread addAltThread = new AddAltThread(this.username.getText(), this.password.getText());
                addAltThread.start();
                break;
            }
            case 1: {
                this.mc.displayGuiScreen(this.manager);
            }
        }
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 92 + 12, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 116 + 12, "Back"));
        this.username = new GuiTextField(this.eventButton, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.password = new PasswordField(this.mc.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
    }

    @Override
    protected void keyTyped(char c, int n) {
        this.username.textboxKeyTyped(c, n);
        this.password.textboxKeyTyped(c, n);
        if (c == '\t' && (this.username.isFocused() || this.password.isFocused())) {
            this.username.setFocused(!this.username.isFocused());
            this.password.setFocused(!this.password.isFocused());
        }
        if (c == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    public GuiAddAlt(GuiAltManager guiAltManager) {
        this.manager = guiAltManager;
    }

    @Override
    protected void mouseClicked(int n, int n2, int n3) {
        try {
            super.mouseClicked(n, n2, n3);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        this.username.mouseClicked(n, n2, n3);
        this.password.mouseClicked(n, n2, n3);
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        this.password.drawTextBox();
        this.drawCenteredString(this.fontRendererObj, "Add Alt", this.width / 2, 20, -1);
        if (this.username.getText().isEmpty()) {
            this.drawString(this.mc.fontRendererObj, "Username / E-Mail", this.width / 2 - 96, 66, -7829368);
        }
        if (this.password.getText().isEmpty()) {
            this.drawString(this.mc.fontRendererObj, "Password", this.width / 2 - 96, 106, -7829368);
        }
        this.drawCenteredString(this.fontRendererObj, this.status, this.width / 2, 30, -1);
        super.drawScreen(n, n2, f);
    }

    static void access$0(GuiAddAlt guiAddAlt, String string) {
        guiAddAlt.status = string;
    }

    private class AddAltThread
    extends Thread {
        private final String username;
        private final String password;

        public AddAltThread(String string, String string2) {
            this.username = string;
            this.password = string2;
            GuiAddAlt.access$0(GuiAddAlt.this, String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GRAY).append("Idle...")));
        }

        private final void checkAndAddAlt(String string, String string2) throws IOException {
            YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
            YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
            yggdrasilUserAuthentication.setUsername(string);
            yggdrasilUserAuthentication.setPassword(string2);
            try {
                yggdrasilUserAuthentication.logIn();
                AltManager.registry.add(new Alt(string, string2, yggdrasilUserAuthentication.getSelectedProfile().getName()));
                GuiAddAlt.access$0(GuiAddAlt.this, String.valueOf(new StringBuilder("Alt added. (").append(string).append(")")));
            }
            catch (AuthenticationException authenticationException) {
                GuiAddAlt.access$0(GuiAddAlt.this, String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.RED).append("Alt failed!")));
                authenticationException.printStackTrace();
            }
        }

        @Override
        public void run() {
            if (this.password.equals("")) {
                AltManager.registry.add(new Alt(this.username, ""));
                GuiAddAlt.access$0(GuiAddAlt.this, String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.GREEN).append("Alt added. (").append(this.username).append(" - offline name)")));
                return;
            }
            GuiAddAlt.access$0(GuiAddAlt.this, String.valueOf(new StringBuilder().append((Object)EnumChatFormatting.YELLOW).append("Trying alt...")));
            try {
                this.checkAndAddAlt(this.username, this.password);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }
}

