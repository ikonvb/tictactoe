package com.konstantinbulygin.tictactoexml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id = 0;
    private String name;
    private String symbol;
    private Step step;
}
