package com.github.soravoid.exceed.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ExceedEvents
{
    public static class EffervescenceChangedEvent extends Event
    {
        public PlayerEntity player;

        public EffervescenceChangedEvent(PlayerEntity player)
        {
            this.player = player;
        }
    }
}
