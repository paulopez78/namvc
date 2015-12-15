package namvc.controllers;

import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcController;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.SetSessionAction;

import java.io.IOException;

public class LogoutController extends NaMvcController {

  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext) {
    session.kill(httpContext.getSessionId());
    NaMvcAction setSessionAction = new SetSessionAction(
            httpContext.getSessionId(),
            session.getCookieName(),
            session.getTimeout());

    try {
      setSessionAction.execute(httpContext.getResponse());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new RedirectAction("/login");
  }
}