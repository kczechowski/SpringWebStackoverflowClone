package com.kczechowski.stackclone.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private LocalDate createdAt;

  @Column(name = "updated_at")
  private LocalDate updatedAt;

  @Column(name = "title")
  private String title;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "questions_tags",
          joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
  inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
  @JsonIgnore
  private List<Tag> tags;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "question_id")
  private List<Answer> answers;
  
}