package com.klobbix.gaming.item;

public abstract class AbstractItem implements Item {

    private int amount;
    private ItemMeta meta;


    protected AbstractItem(ItemMeta meta) {
        this.amount = 1;
        this.meta = meta;
    }

    protected AbstractItem(int amount, ItemMeta meta) {
        this.amount = amount;
        this.meta = meta;
    }

    @Override
    public ItemMeta getItemMeta() {
        return meta;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setItemMeta(ItemMeta meta) {
        this.meta = meta;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
