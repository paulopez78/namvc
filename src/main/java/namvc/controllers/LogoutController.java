package namvc.controllers;

import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcController;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpactions.SetSessionAction;

public class LogoutController extends NaMvcController {

  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    try
    {
      session.kill(httpContext.getSessionId());
      NaMvcAction setSessionAction = new SetSessionAction(
              httpContext.getSessionId(),
              session.getCookieName(),
              session.getTimeout());

      setSessionAction.execute(httpContext.getResponse());
      return new RedirectAction("/login");
    }
    catch(Exception ex)
    {
      return new RenderAction(View.render("Logout error"));
    }
  }
}