package com.kczechowski.stackclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kczechowski.stackclone.entities.QuestionReaction;

public interface QuestionReactionRepository extends JpaRepository<QuestionReaction, Integer>, JpaSpecificationExecutor<QuestionReaction> {

}