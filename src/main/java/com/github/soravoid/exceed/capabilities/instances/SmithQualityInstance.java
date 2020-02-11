package com.github.soravoid.exceed.capabilities.instances;

import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.util.SmithQuality;

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
}
