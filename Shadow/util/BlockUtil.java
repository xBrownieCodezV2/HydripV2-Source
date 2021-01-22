/*
 * Decompiled with CFR 0.150.
 */
package Shadow.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class BlockUtil {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static float[] getDirectionToBlock(int n, int n2, int n3, EnumFacing enumFacing) {
        EntityEgg entityEgg = new EntityEgg(BlockUtil.mc.theWorld);
        entityEgg.posX = (double)n + 0.5;
        entityEgg.posY = (double)n2 + 0.5;
        entityEgg.posZ = (double)n3 + 0.5;
        entityEgg.posX += (double)enumFacing.getDirectionVec().getX() * 0.25;
        entityEgg.posY += (double)enumFacing.getDirectionVec().getY() * 0.25;
        entityEgg.posZ += (double)enumFacing.getDirectionVec().getZ() * 0.25;
        return BlockUtil.getDirectionToEntity(entityEgg);
    }

    public static float getYaw(Entity entity) {
        double d = entity.posX - BlockUtil.mc.thePlayer.posX;
        double d2 = entity.posZ - BlockUtil.mc.thePlayer.posZ;
        double d3 = d2 < 0.0 && d < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d2 / d)) : (d2 < 0.0 && d > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d2 / d)) : Math.toDegrees(-Math.atan(d / d2)));
        return MathHelper.wrapAngleTo180_float(-(BlockUtil.mc.thePlayer.rotationYaw - (float)d3));
    }

    public static float getPitch(Entity entity) {
        double d = entity.posX - BlockUtil.mc.thePlayer.posX;
        double d2 = entity.posZ - BlockUtil.mc.thePlayer.posZ;
        double d3 = entity.posY - 1.6 + (double)entity.getEyeHeight() - BlockUtil.mc.thePlayer.posY;
        double d4 = MathHelper.sqrt_double(d * d + d2 * d2);
        double d5 = -Math.toDegrees(Math.atan(d3 / d4));
        return -MathHelper.wrapAngleTo180_float(BlockUtil.mc.thePlayer.rotationPitch - (float)d5);
    }

    private static float[] getDirectionToEntity(Entity entity) {
        return new float[]{BlockUtil.getYaw(entity) + BlockUtil.mc.thePlayer.rotationYaw, BlockUtil.getPitch(entity) + BlockUtil.mc.thePlayer.rotationPitch};
    }

    public static float[] getRotationNeededForBlock(EntityPlayer entityPlayer, BlockPos blockPos) {
        double d = (double)blockPos.getX() - entityPlayer.posX;
        double d2 = (double)blockPos.getY() + 0.5 - (entityPlayer.posY + (double)entityPlayer.getEyeHeight());
        double d3 = (double)blockPos.getZ() - entityPlayer.posZ;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)(Math.atan2(d3, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d2, d4) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }
}

