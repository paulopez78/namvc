package main.java.namvc.framework;

import main.java.namvc.Users;

public interface NaMvcContext  {
  NaMvcHttpSession getSession();
  Users getUsers();
}
