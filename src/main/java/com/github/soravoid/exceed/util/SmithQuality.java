package com.github.soravoid.exceed.util;

import net.minecraft.util.text.TextFormatting;

public enum SmithQuality
{
    SHARP(TextFormatting.AQUA + "Sharp"), // % increase in damage
    CHARRED(TextFormatting.DARK_RED + "Charred"), // Fire damage and/or set target on fire
    DULL(TextFormatting.GRAY + "Dull"), // % or flat decrease in damage
    RISKY(TextFormatting.RED + "Risky"), // Chance for self-damage, but more dmg output
    ;

    String name;

    SmithQuality(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }
}
