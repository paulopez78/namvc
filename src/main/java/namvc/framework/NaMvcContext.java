package namvc.framework;

import namvc.Users;

public interface NaMvcContext  {
  NaMvcHttpSession getSession();
  Users getUsers();
}
