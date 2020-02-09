package com.kczechowski.stackclone.controllers;

import com.kczechowski.stackclone.entities.Answer;
import com.kczechowski.stackclone.entities.Question;
import com.kczechowski.stackclone.entities.Tag;
import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.entities.requests.AddQuestionRequest;
import com.kczechowski.stackclone.exceptions.NotFoundException;
import com.kczechowski.stackclone.repositories.AnswerRepository;
import com.kczechowski.stackclone.repositories.QuestionRepository;
import com.kczechowski.stackclone.repositories.TagRepository;
import com.kczechowski.stackclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/questions")
    List<Question> all() {
        return questionRepository.findAll();
    }

    @PostMapping("/questions")
    Question newQuestion(@RequestBody AddQuestionRequest questionRequest) {

        List<Tag> tags = new ArrayList<>();

        Arrays.stream(questionRequest.getTags()).forEach(i -> {
            tags.add(tagRepository.findTagById(i));
        });

        Question question = new Question();
        question.setContent(questionRequest.getContent());
        question.setTitle(questionRequest.getTitle());
        question.setTags(tags);
        question.setCreatedAt(LocalDate.now());
        question.setUpdatedAt(LocalDate.now());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = userRepository.findByNickname(securityContext.getAuthentication().getName());

        question.setUserId(user.getId());

        return questionRepository.save(question);
    }

    @PostMapping("/questions/answers")
    Answer newAnswer(@RequestBody Answer answer) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = userRepository.findByNickname(securityContext.getAuthentication().getName());

        answer.setUserId(user.getId());

        return answerRepository.save(answer);
    }

    @GetMapping("/questions/{id}")
    Question one(@PathVariable int id) {

        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
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
