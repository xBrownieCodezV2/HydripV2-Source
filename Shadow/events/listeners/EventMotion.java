/*
 * Decompiled with CFR 0.150.
 */
package Shadow.events.listeners;

import Shadow.events.Event;

public class EventMotion
extends Event<EventMotion> {
    public double x;
    public double y;
    public boolean onGround;
    public double z;
    public float yaw;
    public float pitch;

    public EventMotion(double d, double d2, double d3, float f, float f2, boolean bl) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.yaw = f;
        this.pitch = f2;
        this.onGround = bl;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setZ(double d) {
        this.z = d;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public double getX() {
        return this.x;
    }

    public void setY(double d) {
        this.y = d;
    }

    public void setOnGround(boolean bl) {
        this.onGround = bl;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public void setX(double d) {
        this.x = d;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }
}

