package com.konstantinbulygin.tictactoexml.model.restapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPlayer {

    private String playerName;
    private String symbol;

    public LoginPlayer(String playerName, String symbol) {
        this.playerName = playerName;
        this.symbol = symbol;
    }

    public LoginPlayer() {
    }
}
