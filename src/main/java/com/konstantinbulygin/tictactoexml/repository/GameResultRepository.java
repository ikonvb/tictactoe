package com.konstantinbulygin.tictactoexml.repository;

import com.konstantinbulygin.tictactoexml.model.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Integer> {
}
