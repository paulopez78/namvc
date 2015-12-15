package namvc;

import namvc.framework.NaMvcApp;
import namvc.controllers.HomeController;
import namvc.controllers.LogoutController;
import namvc.controllers.PageController;
import namvc.controllers.LoginController;
import namvc.repositories.UsersInMemoryRepository;
import namvc.repositories.UsersRepository;
import namvc.repositories.UsersRepositoryFactory;

public class App {
  public static void main(String[] args) throws Exception {

    try
    {
      NaMvcApp app = new NaMvcApp(8000, 300);

      UsersRepositoryFactory usersFactory = new UsersRepositoryFactory();
      UsersRepository usersRepository = usersFactory.create(UsersInMemoryRepository.class);

      app.route("/page1", new PageController(), "Role1");
      app.route("/page2", new PageController(), "Role2");
      app.route("/page3", new PageController(), "Role3");
      app.route("/", new HomeController(), "Any");
      app.route("/login", new LoginController(usersRepository));
      app.route("/logout", new LogoutController());

      app.start();
    }
    catch (Exception ex)
    {
      System.out.println(ex);
    }
  }
}
