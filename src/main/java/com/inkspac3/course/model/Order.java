package com.inkspac3.course.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
  private Long id;
  private Long userId;
  private Long beatId;
  private BigDecimal price;
  private LocalDateTime createdAt;
  private String beatTitle;

  public Order() {}

  public Order(Long id, Long userId, Long beatId, BigDecimal price, LocalDateTime createdAt, String beatTitle) {
    this.id = id;
    this.userId = userId;
    this.beatId = beatId;
    this.price = price;
    this.createdAt = createdAt;
    this.beatTitle = beatTitle;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getBeatId() {
    return beatId;
  }

  public void setBeatId(Long beatId) {
    this.beatId = beatId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getBeatTitle() {
    return beatTitle;
  }

  public void setBeatTitle(String beatTitle) {
    this.beatTitle = beatTitle;
  }
}
