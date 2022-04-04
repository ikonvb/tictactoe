package com.konstantinbulygin.tictactoexml.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface GameDocumentReader {

    void readGameFile(String fileName);
}
