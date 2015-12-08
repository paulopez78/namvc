package namvc.controllers;

import namvc.NaMvcContext;
import namvc.NaMvcHttpContext;
import namvc.NaMvcPrincipal;
import namvc.views.LoginView;
import java.util.Map;

public class LoginController extends NaMvcController{
  public LoginController()
  {
    this.View = new LoginView();
  }

  @Override
  public NaMvcAction postAction(NaMvcContext context, NaMvcHttpContext httpContext)
  {
    try
    {
      String login = httpContext.getParameters().get("login").toString();
      String password = httpContext.getParameters().get("password").toString();

      NaMvcPrincipal principal = context.getUsers().authenticate(login, password);
      String sessionId = context.getSession().create(principal);

      String redirectUrl = "/";
      if (httpContext.getParameters().containsKey("redirectUrl"))
      {
        redirectUrl = httpContext.getParameters().get("redirectUrl").toString();
      }

      //redirect
      return new SetSessionAction(sessionId, context.getSession().COOKIE_NAME,
              context.getSession().getTimeout(), redirectUrl);

    }
    catch(Exception ex)
    {
      return new RenderAction(View.render("Login error"));
    }
  }
}
