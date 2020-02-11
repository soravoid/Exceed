package com.github.soravoid.exceed.capabilities.interfaces;

public interface ICharges
{
    int getCharges();

    void setCharges(int amt);
    void addCharges(int amt);
    void removeCharges(int amt);
    void discharge();
}
