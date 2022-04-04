package com.konstantinbulygin.tictactoexml.repository;

import com.konstantinbulygin.tictactoexml.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
