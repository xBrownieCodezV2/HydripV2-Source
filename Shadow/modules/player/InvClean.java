/*
 * Decompiled with CFR 0.150.
 */
package Shadow.modules.player;

import Shadow.events.Event;
import Shadow.events.listeners.EventUpdate;
import Shadow.modules.Module;
import Shadow.util.Timer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class InvClean
extends Module {
    public float bestSwordDamage;
    public boolean preferSwords = true;
    public int[] bestArmorDamageReducement;
    public List<Integer> trash = new ArrayList<Integer>();
    public Timer timer = new Timer();
    public int bestSwordSlot;
    public int[] bestArmorSlots;

    @Override
    public void onDisable() {
    }

    public InvClean() {
        super("InvClean", 0, Module.Category.PLAYER);
    }

    private void searchForItems() {
        Item item;
        ItemStack itemStack;
        int n;
        this.bestArmorDamageReducement = new int[4];
        this.bestArmorSlots = new int[4];
        this.bestSwordDamage = -1.0f;
        this.bestSwordSlot = -1;
        Arrays.fill(this.bestArmorDamageReducement, -1);
        Arrays.fill(this.bestArmorSlots, -1);
        for (n = 0; n < this.bestArmorSlots.length; ++n) {
            itemStack = this.mc.thePlayer.inventory.armorItemInSlot(n);
            if (itemStack == null || itemStack.getItem() == null || !(itemStack.getItem() instanceof ItemArmor)) continue;
            item = (ItemArmor)itemStack.getItem();
            this.bestArmorDamageReducement[n] = ((ItemArmor)item).damageReduceAmount;
        }
        for (n = 0; n < 36; ++n) {
            itemStack = this.mc.thePlayer.inventory.getStackInSlot(n);
            if (itemStack == null || itemStack.getItem() == null) continue;
            if (itemStack.getItem() instanceof ItemArmor) {
                item = (ItemArmor)itemStack.getItem();
                int n2 = 3 - ((ItemArmor)item).armorType;
                if (this.bestArmorDamageReducement[n2] < ((ItemArmor)item).damageReduceAmount) {
                    this.bestArmorDamageReducement[n2] = ((ItemArmor)item).damageReduceAmount;
                    this.bestArmorSlots[n2] = n;
                }
            }
            if (itemStack.getItem() instanceof ItemSword && this.bestSwordDamage < ((ItemSword)(item = (ItemSword)itemStack.getItem())).getDamageVsEntity()) {
                this.bestSwordDamage = ((ItemSword)item).getDamageVsEntity();
                this.bestSwordSlot = n;
            }
            if (!(itemStack.getItem() instanceof ItemTool)) continue;
            item = (ItemTool)itemStack.getItem();
            float f = ((ItemTool)item).getToolMaterial().getDamageVsEntity();
            if (this.preferSwords) {
                f -= 1.0f;
            }
            if (!(this.bestSwordDamage < f)) continue;
            this.bestSwordDamage = f;
            this.bestSwordSlot = n;
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate && event.isPre()) {
            this.searchForItems();
            for (int i = 0; i < this.bestArmorSlots.length; ++i) {
                if (this.bestArmorSlots[i] == -1) continue;
                int n = this.bestArmorSlots[i];
                ItemStack itemStack = this.mc.thePlayer.inventory.armorItemInSlot(i);
                if (itemStack != null && itemStack.getItem() != null && this.timer.hasTimedElapsed(35L, false)) {
                    this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, 8 - i, 0, 1, this.mc.thePlayer);
                }
                if (!this.timer.hasTimedElapsed(55L, false)) continue;
                this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, n < 9 ? n + 36 : n, 0, 1, this.mc.thePlayer);
            }
            if (this.bestSwordSlot != -1 && this.bestSwordDamage != -1.0f && this.timer.hasTimedElapsed(75L, false)) {
                this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, this.bestSwordSlot < 9 ? this.bestSwordSlot + 36 : this.bestSwordSlot, 0, 2, this.mc.thePlayer);
            }
            this.searchForTrash();
            for (Integer n : this.trash) {
                if (!this.timer.hasTimedElapsed(95L, false)) continue;
                this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, n < 9 ? n + 36 : n, 0, 4, this.mc.thePlayer);
                this.timer.reset();
            }
        }
    }

    private void searchForTrash() {
        Item item;
        Object object;
        int n3;
        this.trash.clear();
        this.bestArmorDamageReducement = new int[4];
        this.bestArmorSlots = new int[4];
        this.bestSwordDamage = -1.0f;
        this.bestSwordSlot = -1;
        Arrays.fill(this.bestArmorDamageReducement, -1);
        Arrays.fill(this.bestArmorSlots, -1);
        List[] arrlist = new List[4];
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (n3 = 0; n3 < this.bestArmorSlots.length; ++n3) {
            object = this.mc.thePlayer.inventory.armorItemInSlot(n3);
            arrlist[n3] = new ArrayList();
            if (object == null || ((ItemStack)object).getItem() == null || !(((ItemStack)object).getItem() instanceof ItemArmor)) continue;
            item = (ItemArmor)((ItemStack)object).getItem();
            this.bestArmorDamageReducement[n3] = ((ItemArmor)item).damageReduceAmount;
            this.bestArmorSlots[n3] = 8 + n3;
        }
        for (n3 = 0; n3 < 36; ++n3) {
            object = this.mc.thePlayer.inventory.getStackInSlot(n3);
            if (object == null || ((ItemStack)object).getItem() == null) continue;
            if (((ItemStack)object).getItem() instanceof ItemArmor) {
                item = (ItemArmor)((ItemStack)object).getItem();
                int n4 = 3 - ((ItemArmor)item).armorType;
                arrlist[n4].add(n3);
                if (this.bestArmorDamageReducement[n4] < ((ItemArmor)item).damageReduceAmount) {
                    this.bestArmorDamageReducement[n4] = ((ItemArmor)item).damageReduceAmount;
                    this.bestArmorSlots[n4] = n3;
                }
            }
            if (((ItemStack)object).getItem() instanceof ItemSword) {
                item = (ItemSword)((ItemStack)object).getItem();
                arrayList.add(n3);
                if (this.bestSwordDamage < ((ItemSword)item).getDamageVsEntity()) {
                    this.bestSwordDamage = ((ItemSword)item).getDamageVsEntity();
                    this.bestSwordSlot = n3;
                }
            }
            if (!(((ItemStack)object).getItem() instanceof ItemTool)) continue;
            item = (ItemTool)((ItemStack)object).getItem();
            float f = ((ItemTool)item).getToolMaterial().getDamageVsEntity();
            if (this.preferSwords) {
                f -= 1.0f;
            }
            if (!(this.bestSwordDamage < f)) continue;
            this.bestSwordDamage = f;
            this.bestSwordSlot = n3;
        }
        n3 = 0;
        while (n3 < arrlist.length) {
            object = arrlist[n3];
            int n5 = n3++;
            object.stream().filter(n2 -> n2 != this.bestArmorSlots[n5]).forEach(this.trash::add);
        }
        arrayList.stream().filter(n -> n != this.bestSwordSlot).forEach(this.trash::add);
    }

    @Override
    public void onEnable() {
    }
}

