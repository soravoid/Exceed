package com.github.soravoid.exceed.capabilities.instances;

import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import com.github.soravoid.exceed.events.ExceedEvents;
import com.github.soravoid.exceed.render.RenderEffervescenceBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Random;

public class EffervescenceInstance implements IEffervescence
{
    private int effervescence, maxEffervescence;
    private PlayerEntity player;

    public EffervescenceInstance(PlayerEntity player)
    {
        this.maxEffervescence = new Random().nextInt(1001);
        this.effervescence = maxEffervescence;
        this.player = player;
    }

    public EffervescenceInstance(PlayerEntity player, int maxEffervescence, int effervescence)
    {
        this.player = player;
        this.maxEffervescence = maxEffervescence;
        this.effervescence = effervescence;
    }

    @Override
    public void setPlayer(PlayerEntity player) { this.player = player; }

    @Override
    public int getEffervescence() { return effervescence; }

    @Override
    public int getMaxEffervescence() { return maxEffervescence; }

    @Override
    public void setEffervescence(int value) { this.effervescence = value; }

    @Override
    public void setMaxEffervescence(int maxEffervescence)
    {
        this.maxEffervescence = maxEffervescence;
        MinecraftForge.EVENT_BUS.post(new ExceedEvents.EffervescenceChangedEvent(player));
        RenderEffervescenceBar.timeSinceLastUpdate = 0;
    }

    public void setEffervescencesSilent(int maxEffervescence, int current)
    {
        this.maxEffervescence = maxEffervescence;
        this.effervescence = current;
    }

    @Override
    public boolean consume(int value)
    {
        if(value > effervescence) return false;
        else
        {
            this.effervescence -= value;
            MinecraftForge.EVENT_BUS.post(new ExceedEvents.EffervescenceChangedEvent(player));
            RenderEffervescenceBar.timeSinceLastUpdate = 0;
            return true;
        }
    }

    @Override
    public void fill(int value)
    {
        if(value + effervescence > maxEffervescence) this.effervescence = maxEffervescence;
        else this.effervescence += value;
        MinecraftForge.EVENT_BUS.post(new ExceedEvents.EffervescenceChangedEvent(player));
        RenderEffervescenceBar.timeSinceLastUpdate = 0;
    }

    public static class Storage implements Capability.IStorage<IEffervescence>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IEffervescence> capability, IEffervescence instance, Direction side)
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("maxEffervescence", instance.getMaxEffervescence());
            nbt.putInt("effervescence", instance.getEffervescence());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IEffervescence> capability, IEffervescence instance, Direction side, INBT nbt)
        {
            CompoundNBT nbt1 = (CompoundNBT) nbt;
            instance.setEffervescencesSilent(nbt1.getInt("maxEffervescence"), nbt1.getInt("effervescence"));
        }
    }
}
