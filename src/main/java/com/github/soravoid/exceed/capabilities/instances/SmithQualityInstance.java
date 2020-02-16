package com.github.soravoid.exceed.capabilities.instances;

import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.util.SmithQuality;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Random;

public class SmithQualityInstance implements ISmithQuality
{
    private SmithQuality quality;

    public SmithQualityInstance()
    {
        //TODO Weighted Randomness
        SmithQuality[] vals = SmithQuality.values();
        quality = vals[new Random().nextInt(vals.length)];
    }

    public SmithQualityInstance(SmithQuality quality) { this.quality = quality; }

    @Override
    public SmithQuality getQuality() { return quality; }

    @Override
    public void setQuality(SmithQuality quality) { this.quality = quality; }

    public static class Storage implements Capability.IStorage<ISmithQuality>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ISmithQuality> capability, ISmithQuality instance, Direction side) { return IntNBT.valueOf(instance.getQuality().ordinal()); }

        @Override
        public void readNBT(Capability<ISmithQuality> capability, ISmithQuality instance, Direction side, INBT nbt) { instance.setQuality(SmithQuality.values()[((IntNBT)nbt).getInt()]); }
    }
}
