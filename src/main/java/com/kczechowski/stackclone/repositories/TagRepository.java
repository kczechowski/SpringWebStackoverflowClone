package com.kczechowski.stackclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.kczechowski.stackclone.entities.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {
    Tag findTagById(Integer i);
}