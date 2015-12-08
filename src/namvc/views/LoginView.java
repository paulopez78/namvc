package namvc.views;

public class LoginView extends NaMvcView
{
  public String render(Object model) {
    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    builder.append("<form action='/login' method='post'>");
      builder.append(input("Login", "login", false));
      builder.append(input("Password", "password", true));
      builder.append(submit("Login"));
    builder.append("</form>");

    if (model != null) {
      builder.append("<p>" + model.toString() + "</p>");
    }
    builder.append("</html>");
    return builder.toString();
  }
}
