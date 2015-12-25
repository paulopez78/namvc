package namvc.controllers;

import namvc.framework.*;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.models.LoginModel;
import namvc.repositories.UsersRepository;

public class LoginController extends NaMvcController {
  private final UsersRepository usersRepository;

  public LoginController(UsersRepository usersRepository)
  {
    this.usersRepository = usersRepository;
  }

  @Override
  public NaMvcAction getAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    LoginModel model = new LoginModel(getRedirectUrl(httpContext));
    return new RenderAction(View.render(model));
  }

  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    String redirectUrl = getRedirectUrl(httpContext);

    try
    {
      String login = httpContext.getRequest().getParameters().get("login");
      String password = httpContext.getRequest().getParameters().get("password");

      NaMvcPrincipal principal = usersRepository.authenticate(login, password);
      String sessionId = session.create(principal);
      httpContext.setSessionId(sessionId);
      httpContext.setPrincipal(principal);

      return new RedirectAction(redirectUrl);
    }
    catch(Exception ex)
    {
      LoginModel model = new LoginModel(redirectUrl, "Login error");
      return new RenderAction(View.render(model));
    }
  }

  private String getRedirectUrl(NaMvcHttpContext httpContext) {
    String redirectUrl = "/";
    if (httpContext.getRequest().getParameters().containsKey("redirectUrl"))
    {
      redirectUrl = httpContext.getRequest().getParameters().get("redirectUrl");
    }
    return redirectUrl;
  }
}