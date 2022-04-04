package com.konstantinbulygin.tictactoexml.repository;

import com.konstantinbulygin.tictactoexml.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
