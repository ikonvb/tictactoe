package com.konstantinbulygin.tictactoexml.service;

import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;


    public Optional<Player> findById(Integer playerId) {
        return playerRepository.findById(playerId);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
