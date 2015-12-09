package main.java.namvc;

import main.java.namvc.framework.NaMvcPrincipal;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Users {
  private Map<String,List<String>> userRoles;

  private Map<String,String> userPasswords;

  public void create()
  {
    //TODO: read from a file
    userRoles = new HashMap<String,List<String>>()
    {
      {
          put("user1", new ArrayList<String>(){
            {
              add("Role1");
              add("Role3");
            }
          });
          put("user2", new ArrayList<String>(){
            {
              add("Role2");
              add("Role3");
            }
          });
          put("user3", new ArrayList<String>(){
            {
              add("Role3");
            }
          });
      }
    };

    //TODO: store passwords hashed
    userPasswords = new HashMap<String,String>()
    {
      {
          put("user1","password");
          put("user2","password");
          put("user3","password");
      }
    };
  }

  public NaMvcPrincipal authenticate(String user, String password) throws Exception {
    boolean authenticated = userPasswords.get(user).equals(applyHash(password));

    if (authenticated)
    {
      return new NaMvcPrincipal(user, userRoles.get(user));
    }

    throw new Exception("Authentication failed");
  }

  private String applyHash(String password)
  {
    //TODO: store and compare hashed passwords
    return password;
  }
}
