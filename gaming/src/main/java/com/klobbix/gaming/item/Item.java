package com.klobbix.gaming.item;

public interface Item {

    ItemMeta getItemMeta();

    void setItemMeta(ItemMeta meta);

    int getAmount();

    void setAmount(int amount);
}
