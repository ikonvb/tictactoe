package com.konstantinbulygin.tictactoexml.service;

import com.konstantinbulygin.tictactoexml.model.Game;
import com.konstantinbulygin.tictactoexml.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Game save(Game newGame) {
        return gameRepository.save(newGame);
    }

    public Optional<Game> findById(Integer gameId) {
        return gameRepository.findById(gameId);
    }
}
