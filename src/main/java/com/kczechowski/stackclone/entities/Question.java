package com.kczechowski.stackclone.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Question implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "content")
  private String content;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Integer updatedAt;

  
}