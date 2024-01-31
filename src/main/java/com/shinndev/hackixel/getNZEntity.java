package com.shinndev.hackixel;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.entity.player.EntityPlayer;

public class getNZEntity {
    private Minecraft minecraft;

    public void getMc () {
        minecraft = Minecraft.getMinecraft();
    }

    public void updateEntity() {
        EntityZombie nearestZombie = findNearestZombie();

        if (nearestZombie != null) {
            PathEntity path = minecraft.player.getNavigator().getPathToEntityLiving(nearestZombie);

            if (path != null) {
                minecraft.player.getNavigator().setPath(path, 1.0);
            }
        }
    }

    private EntityZombie findNearestZombie() {
        double closestDistance = Double.MAX_VALUE;
        EntityZombie nearestZombie = null;

        for (Entity entity : minecraft.theWorld.loadedEntityList) {
            if (entity instanceof EntityZombie) {
                double distance = minecraft.player.getDistanceSqToEntity(entity);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    nearestZombie = (EntityZombie) entity;
                }
            }
        }

        return nearestZombie;
    }
}