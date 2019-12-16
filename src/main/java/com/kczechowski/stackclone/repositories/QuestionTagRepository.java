package com.kczechowski.stackclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kczechowski.stackclone.entities.QuestionTag;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Integer>, JpaSpecificationExecutor<QuestionTag> {

}