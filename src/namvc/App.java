package namvc;

import namvc.controllers.HomeController;
import namvc.controllers.LogoutController;
import namvc.controllers.PageController;
import namvc.controllers.LoginController;

public class App {
  public static void main(String[] args) throws Exception {

    NaMvcApp app = new NaMvcApp(8000, 30);

    app.route("/page1", new PageController(), "Role1");
    app.route("/page2", new PageController(), "Role2");
    app.route("/page3", new PageController(), "Role3");
    app.route("/", new HomeController(), "Any");
    app.route("/login", new LoginController());
    app.route("/logout", new LogoutController());

    app.start();
  }
}
