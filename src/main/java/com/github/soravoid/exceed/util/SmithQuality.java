package com.github.soravoid.exceed.util;

public enum SmithQuality
{
    SHARP("Sharp"), // % increase in damage
    CHARRED("Charred"), // Fire damage and/or set target on fire
    DULL("Dull"), // % or flat decrease in damage
    RISKY("Risky"), // Chance for self-damage, but more dmg output
    ;

    String name;

    SmithQuality(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }
}
