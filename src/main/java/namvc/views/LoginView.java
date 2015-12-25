package namvc.views;

import namvc.framework.NaMvcView;
import namvc.models.LoginModel;

public class LoginView extends NaMvcView
{
  public String render(Object model) {
    LoginModel loginModel = (LoginModel) model;

    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    builder.append("<form action='/login' method='post'>");
      builder.append(input("Login", "login", false));
      builder.append(input("Password", "password", true));
      builder.append(hidden("redirectUrl", loginModel.getRedirectUrl()));
      builder.append(submit("Login"));
    builder.append("</form>");

    if (loginModel.hasError()) {
      builder.append("<p>" + loginModel.getErrorMessage() + "</p>");
    }
    builder.append("</html>");
    return builder.toString();
  }
}
