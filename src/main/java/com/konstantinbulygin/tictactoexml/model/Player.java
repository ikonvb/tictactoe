package com.konstantinbulygin.tictactoexml.model;

import java.io.Serializable;

public class Player implements Serializable {

    private int id = 0;
    private String name;
    private String symbol;

    public Player() {
    }

    public Player(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
