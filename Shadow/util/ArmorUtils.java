/*
 * Decompiled with CFR 0.150.
 */
package Shadow.util;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorUtils {
    public static boolean isBetterArmor(int n, int[] arrn) {
        if (Minecraft.getMinecraft().thePlayer.inventory.armorInventory[n] != null) {
            int n2;
            int n3;
            int n4 = 0;
            int n5 = 0;
            int n6 = -1;
            int n7 = -1;
            int[] arrn2 = arrn;
            int n8 = arrn.length;
            for (n3 = 0; n3 < n8; ++n3) {
                n2 = arrn2[n3];
                if (Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.inventory.armorInventory[n].getItem()) == n2) {
                    n6 = n4;
                    break;
                }
                ++n4;
            }
            arrn2 = arrn;
            n8 = arrn.length;
            for (n3 = 0; n3 < n8; ++n3) {
                n2 = arrn2[n3];
                if (ArmorUtils.getItem(n2) != -1) {
                    n7 = n5;
                    break;
                }
                ++n5;
            }
            if (n7 > -1) {
                return n7 < n6;
            }
        }
        return false;
    }

    public static int getItem(int n) {
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemStack == null || Item.getIdFromItem(itemStack.getItem()) != n) continue;
            return i;
        }
        return -1;
    }
}

