package com.github.soravoid.exceed.capabilities.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IEffervescence
{
    int getEffervescence();
    int getMaxEffervescence();

    void setEffervescence(int value);
    void setMaxEffervescence(int value);
    void setPlayer(PlayerEntity player);
    boolean consume(int value); //returns whether there was enough; if true, already performed consumed function
    void fill(int value);
    void setEffervescencesSilent(int maxEffervescence, int current);
}
