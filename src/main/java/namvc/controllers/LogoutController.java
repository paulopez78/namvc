package namvc.controllers;

import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcController;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpactions.RedirectAction;

public class LogoutController extends NaMvcController {
  @Override
  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext) {
    session.kill(httpContext.getSessionId());

    return new RedirectAction("/login");
  }
}