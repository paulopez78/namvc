package namvc.controllers;

import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcController;
import namvc.framework.httpactions.RedirectAction;

public class LogoutController extends NaMvcController {
  @Override
  public NaMvcAction postAction(MvcHttpContext httpContext) {
    httpContext.logout();

    return new RedirectAction("/login");
  }
}