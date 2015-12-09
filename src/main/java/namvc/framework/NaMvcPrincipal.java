package main.java.namvc.framework;

import java.util.List;

public class NaMvcPrincipal {
  private List<String> roles;
  private String userName;

  public NaMvcPrincipal(String user, List<String> roles)
  {
    this.roles = roles;
    this.userName = user;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public boolean isInRole(String role) {
    return roles.contains(role);
  }
}
