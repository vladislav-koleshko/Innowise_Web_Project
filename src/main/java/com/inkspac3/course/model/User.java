package com.inkspac3.course.model;

public class User {
  private Long id;
  private String name;
  private String email;
  private String passwordHash;
  private Role role;

  public enum Role{
    CLIENT, ADMIN
  }

  public User() {}

  public User(Long id, String name, String email, String passwordHash, Role role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {return name;}

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {this.email = email;}

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("ID: " + id + "\n");
    sb.append("Name: " + name + "\n");
    sb.append("Email: " + email + "\n");
    sb.append("Password: " + passwordHash + "\n");
    sb.append("Role: " + role + "\n");
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    User user = (User) obj;

    return id == user.id;
  }

  @Override
  public int hashCode() {
   return Long.hashCode(id);
  }
}
