package namvc.controllers;

import namvc.Users;
import namvc.framework.*;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpactions.SetSessionAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.views.LoginView;

public class LoginController extends NaMvcController {
  private final Users users;

  public LoginController(Users users)
  {
    this.View = new LoginView();
    this.users = users;
  }

  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    try
    {
      String login = httpContext.getRequest().getParameters().get("login");
      String password = httpContext.getRequest().getParameters().get("password");

      NaMvcPrincipal principal = users.authenticate(login, password);
      String sessionId = session.create(principal);

      String redirectUrl = "/";
      if (httpContext.getRequest().getParameters().containsKey("redirectUrl"))
      {
        redirectUrl = httpContext.getRequest().getParameters().get("redirectUrl");
      }

      NaMvcAction setSessionAction = new SetSessionAction(
              sessionId,
              session.getCookieName(),
              session.getTimeout());
      setSessionAction.execute(httpContext.getResponse());
      return new RedirectAction(redirectUrl);
    }
    catch(Exception ex)
    {
      return new RenderAction(View.render("Login error"));
    }
  }
}
