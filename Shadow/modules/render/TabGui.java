/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.render;

import Shadow.Client;
import Shadow.events.Event;
import Shadow.events.listeners.EventKey;
import Shadow.events.listeners.EventRenderGUI;
import Shadow.modules.Module;
import Shadow.settings.BooleanSetting;
import Shadow.settings.ModeSetting;
import Shadow.settings.NumberSetting;
import Shadow.settings.Setting;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGui
extends Module {
    public boolean expanded;
    public int currentTab;

    public TabGui() {
        super("TabGui", 0, Module.Category.RENDER);
        this.toggled = true;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRenderGUI && !this.mc.gameSettings.showDebugInfo) {
            FontRenderer fontRenderer = this.mc.fontRendererObj;
            int n = -1879031664;
            int n2 = -1879035760;
            Gui.drawRect(5.0, 31.0, 70.0, 30 + Module.Category.values().length * 16 + 1, -15790321);
            Gui.drawRect(6.0, 33 + this.currentTab * 16, 70.0, 35 + this.currentTab * 16 + 12, -1879031664);
            int n3 = 0;
            for (Module.Category object2 : Module.Category.values()) {
                fontRenderer.drawString(object2.name, 10.0, 36 + n3 * 16, -1);
                ++n3;
            }
            if (this.expanded) {
                Module.Category category = Module.Category.values()[this.currentTab];
                List<Module> list = Client.getModulesByCategory(category);
                if (list.size() == 0) {
                    return;
                }
                Gui.drawRect(70.0, 31.0, 138.0, 30 + list.size() * 16 + 1, -15790321);
                Gui.drawRect(70.0, 33 + category.moduleIndex * 16, 138.0, 35 + category.moduleIndex * 16 + 12, n);
                n3 = 0;
                for (Module module : list) {
                    fontRenderer.drawString(module.name, 78.0, 36 + n3 * 16, -1);
                    if (n3 == category.moduleIndex && module.expanded) {
                        Setting setting;
                        int n4 = 0;
                        int n5 = 0;
                        for (Setting setting2 : module.settings) {
                            if (setting2 instanceof BooleanSetting) {
                                setting = (BooleanSetting)setting2;
                                if (n5 < fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((BooleanSetting)setting).enabled ? "On" : "Off")))) {
                                    n5 = fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((BooleanSetting)setting).enabled ? "On" : "Off")));
                                }
                            }
                            if (setting2 instanceof NumberSetting) {
                                setting = (NumberSetting)setting2;
                                if (n5 < fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((NumberSetting)setting).getValue())))) {
                                    n5 = fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((NumberSetting)setting).getValue())));
                                }
                            }
                            if (setting2 instanceof ModeSetting) {
                                setting = (ModeSetting)setting2;
                                if (n5 < fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((ModeSetting)setting).modes.get(((ModeSetting)setting).index))))) {
                                    n5 = fontRenderer.getStringWidth(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((ModeSetting)setting).modes.get(((ModeSetting)setting).index))));
                                }
                            }
                            ++n4;
                        }
                        Gui.drawRect(138.0, 31.0, 70 + n5 + 68 + 14, 30 + module.settings.size() * 16 + 1, -15790321);
                        Gui.drawRect(138.0, 33 + module.index * 16, 70 + n5 + 68 + 14, 35 + module.index * 16 + 12, n);
                        n4 = 0;
                        for (Setting setting2 : module.settings) {
                            if (setting2 instanceof BooleanSetting) {
                                setting = (BooleanSetting)setting2;
                                fontRenderer.drawString(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((BooleanSetting)setting).enabled ? "On" : "Off")), 144.0, 35 + n4 * 16, -1);
                            }
                            if (setting2 instanceof NumberSetting) {
                                setting = (NumberSetting)setting2;
                                fontRenderer.drawString(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((NumberSetting)setting).getValue())), 145.0, 35 + n4 * 16, -1);
                            }
                            if (setting2 instanceof ModeSetting) {
                                setting = (ModeSetting)setting2;
                                fontRenderer.drawString(String.valueOf(new StringBuilder(String.valueOf(setting2.name)).append(": ").append(((ModeSetting)setting).modes.get(((ModeSetting)setting).index))), 145.0, 35 + n4 * 16, -1);
                            }
                            ++n4;
                        }
                    }
                    ++n3;
                }
            }
        }
        if (event instanceof EventKey) {
            int n = ((EventKey)event).code;
            Module.Category category = Module.Category.values()[this.currentTab];
            List<Module> list = Client.getModulesByCategory(category);
            if (n == 200) {
                if (this.expanded) {
                    if (this.expanded && !list.isEmpty() && list.get((int)category.moduleIndex).expanded) {
                        Module module = list.get(category.moduleIndex);
                        if (module.settings.get((int)module.index).focused) {
                            Setting setting = module.settings.get(module.index);
                            if (setting instanceof NumberSetting) {
                                ((NumberSetting)setting).increment(true);
                            }
                        } else {
                            module.index = module.index <= 0 ? module.settings.size() - 1 : --module.index;
                        }
                    } else {
                        category.moduleIndex = category.moduleIndex <= 0 ? list.size() - 1 : --category.moduleIndex;
                    }
                } else {
                    this.currentTab = this.currentTab <= 0 ? Module.Category.values().length - 1 : --this.currentTab;
                }
            }
            if (n == 208) {
                if (this.expanded) {
                    if (this.expanded && !list.isEmpty() && list.get((int)category.moduleIndex).expanded) {
                        Module module = list.get(category.moduleIndex);
                        if (module.settings.get((int)module.index).focused) {
                            Setting setting = module.settings.get(module.index);
                            if (setting instanceof NumberSetting) {
                                ((NumberSetting)setting).increment(false);
                            }
                        } else {
                            module.index = module.index >= module.settings.size() - 1 ? 0 : ++module.index;
                        }
                    } else {
                        category.moduleIndex = category.moduleIndex >= list.size() - 1 ? 0 : ++category.moduleIndex;
                    }
                } else {
                    this.currentTab = this.currentTab >= Module.Category.values().length - 1 ? 0 : ++this.currentTab;
                }
            }
            if (n == 28 && this.expanded && list.size() != 0) {
                Module module = list.get(category.moduleIndex);
                if (!module.expanded && !module.settings.isEmpty()) {
                    module.expanded = true;
                } else if (module.expanded) {
                    boolean bl = module.settings.get((int)module.index).focused = !module.settings.get((int)module.index).focused;
                }
            }
            if (n == 205) {
                if (this.expanded && list.size() != 0) {
                    Module module = list.get(category.moduleIndex);
                    if (this.expanded && !list.isEmpty() && module.expanded) {
                        Setting setting = module.settings.get(module.index);
                        if (setting instanceof BooleanSetting) {
                            ((BooleanSetting)setting).toggle();
                        }
                        if (setting instanceof ModeSetting) {
                            ((ModeSetting)setting).cycle();
                        }
                        boolean cfr_ignored_0 = setting instanceof NumberSetting;
                    } else if (!module.name.equals("TabGui")) {
                        module.toggle();
                    }
                } else {
                    this.expanded = true;
                }
            }
            if (n == 203) {
                if (this.expanded && !list.isEmpty() && list.get((int)category.moduleIndex).expanded) {
                    Module module = list.get(category.moduleIndex);
                    if (!module.settings.get((int)module.index).focused) {
                        list.get((int)category.moduleIndex).expanded = false;
                    }
                } else {
                    this.expanded = false;
                }
            }
        }
    }
}

