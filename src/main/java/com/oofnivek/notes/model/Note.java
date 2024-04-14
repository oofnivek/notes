package com.oofnivek.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {

  @JsonIgnore private @Id @GeneratedValue Long id;
  private String name;
  private String desc;

  Note() {}

  Note(String name, String desc) {

    this.name = name;
    this.desc = desc;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
