package com.kczechowski.stackclone.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kczechowski.stackclone.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
    Page<Question> findByUserId(Integer userId, Pageable pageable);

    @Query(
            value = "SELECT q FROM Question q LEFT JOIN q.answers as a WHERE q.userId = ?1 group by q.id order by count(a) DESC"
    )
    Page<Question> findByUserIdMaxAnswers(Integer userId, Pageable pageable);

    Page<Question> findAllByContentContainingOrTitleContaining(String content, String title, Pageable pageable);

}