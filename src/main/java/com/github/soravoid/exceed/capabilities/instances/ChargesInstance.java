package com.github.soravoid.exceed.capabilities.instances;

import com.github.soravoid.exceed.capabilities.interfaces.ICharges;

public class ChargesInstance implements ICharges
{
    private int charges = 0;

    @Override
    public int getCharges() { return charges; }

    @Override
    public void setCharges(int amt) { this.charges = amt; }

    @Override
    public void addCharges(int amt) { this.charges += amt; }

    @Override
    public void removeCharges(int amt) { this.charges -= amt; }

    @Override
    public void discharge() { this.charges = 0; }
}
