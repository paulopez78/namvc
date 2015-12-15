package namvc.controllers;

import namvc.framework.*;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpactions.SetSessionAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.repositories.UsersRepository;

public class LoginController extends NaMvcController {
  private final UsersRepository usersRepository;

  public LoginController(UsersRepository usersRepository)
  {
    this.usersRepository = usersRepository;
  }

  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    try
    {
      String login = httpContext.getRequest().getParameters().get("login");
      String password = httpContext.getRequest().getParameters().get("password");

      NaMvcPrincipal principal = usersRepository.authenticate(login, password);
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
