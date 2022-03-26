package com.konstantinbulygin.tictactoexml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    private String num;
    private String playerId;
    private String step;
}
