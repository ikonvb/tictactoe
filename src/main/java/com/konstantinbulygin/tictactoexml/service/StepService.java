package com.konstantinbulygin.tictactoexml.service;

import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepService {

    @Autowired
    StepRepository stepRepository;

    public Step save(Step step) {
        return stepRepository.save(step);
    }
}
