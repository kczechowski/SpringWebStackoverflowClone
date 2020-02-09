package com.kczechowski.stackclone.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tags")
public class Tag implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "tags")
  private List<Question> questions;

}