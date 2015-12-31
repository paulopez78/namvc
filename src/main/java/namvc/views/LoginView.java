package namvc.views;

import namvc.framework.NaMvcView;
import namvc.models.LoginModel;

public class LoginView extends NaMvcView
{
  public String render(Object model) {
    LoginModel loginModel = (LoginModel) model;

    StringBuilder builder = new StringBuilder();
    builder.append("<html>");

    if (loginModel.authenticated())
    {
      renderAuthenticated(loginModel, builder);
    }
    else
    {
      renderNotAuthenticated(loginModel, builder);
    }

    builder.append("</html>");
    return builder.toString();
  }

  private void renderNotAuthenticated(LoginModel loginModel, StringBuilder builder)
  {
    builder.append("<form action='/login' method='post'>");
    builder.append(input("Login", "login", false));
    builder.append(input("Password", "password", true));
    builder.append(hidden("redirectUrl", loginModel.getRedirectUrl()));
    builder.append(submit("Login"));
    builder.append("</form>");

    if (loginModel.hasError()) {
      builder.append("<p>" + loginModel.getErrorMessage() + "</p>");
    }
  }

  private void renderAuthenticated(LoginModel loginModel, StringBuilder builder) {
    builder.append("<p>Logged in as: " + loginModel.getUser() + "</p>");

    builder.append("<form action='/logout' method='post'>");
    builder.append(submit("Logout"));
    builder.append("</form>");
  }
}
