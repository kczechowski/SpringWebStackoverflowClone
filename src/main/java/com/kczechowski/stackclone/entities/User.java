package com.kczechowski.stackclone.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private List<UserRole> roles;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Question> questions;

  public void setPassword(String password) {
    this.password = PASSWORD_ENCODER.encode(password);
  }
  
}