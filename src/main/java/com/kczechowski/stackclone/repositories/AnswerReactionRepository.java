package com.kczechowski.stackclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kczechowski.stackclone.entities.AnswerReaction;

public interface AnswerReactionRepository extends JpaRepository<AnswerReaction, Integer>, JpaSpecificationExecutor<AnswerReaction> {

}