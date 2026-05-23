package com.vitorlazarini.entities;

public class Sku {

    private String name;
    private String current_price;
    private String old_price;
    private Boolean available;

    /*
    NAME: Nome da variação

    CURRENT_PRICE: Preço atual do produto. Pode ser NULL se não estiver disponível.

    OLD_PRICE: Preço antigo do produto. Pode ser NULL se não estiver disponível.

    AVAILABLE: true/false se o produto está ou não disponível em estoque. */

    public Sku(){

    }

    public Sku(String name, String current_price, String old_price, Boolean available) {
        this.name = name;
        this.current_price = current_price;
        this.old_price = old_price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Sku [name=" + name + ", current_price=" + current_price + ", old_price=" + old_price + ", available="
                + available + "]";
    }

    

   

    
}
