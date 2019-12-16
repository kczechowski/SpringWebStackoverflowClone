package com.kczechowski.stackclone.controllers;

import com.kczechowski.stackclone.entities.Question;
import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    List<Question> all() {
        return questionRepository.findAll();
    }

    @PostMapping("/questions")
    Question newQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @GetMapping("/questions/{id}")
    Question one(@PathVariable int id) {

        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }


    @PutMapping("/questions/{id}")
    Question replaceQuestion(@RequestBody Question question, @PathVariable int id) {

        return questionRepository.findById(id)
                .map(u -> {
                    u.setContent(question.getContent());
                    return questionRepository.save(u);
                }).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/questions/{id}")
    void deleteQuestion(@PathVariable int id) {
        questionRepository.deleteById(id);
    }
}
