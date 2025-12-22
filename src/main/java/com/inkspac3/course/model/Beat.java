package com.inkspac3.course.model;

import java.math.BigDecimal;

public class Beat {
  private Long id;
  private String title;
  private String author;
  private BigDecimal price;
  private String path;

  public Beat() {}

  public Beat(String title, String author, BigDecimal price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
