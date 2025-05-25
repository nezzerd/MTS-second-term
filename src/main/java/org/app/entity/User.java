package org.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Integer id;
  private String name;
  private University university;
  private Set<Course> courses;
  private Set<Book> books;
}
