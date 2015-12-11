package namvc.controllers;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcContext;
import namvc.framework.NaMvcController;
import namvc.framework.NaMvcHttpContext;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpactions.SetSessionAction;

public class LogoutController extends NaMvcController {

  @Override
  public NaMvcAction postAction(NaMvcContext context, NaMvcHttpContext httpContext)
  {
    try
    {
      context.getSession().kill(httpContext.getSessionId());
      return new SetSessionAction(httpContext.getSessionId(), context.getSession().COOKIE_NAME, 0, "/login");

    }
    catch(Exception ex)
    {
      return new RenderAction(View.render("Login error"));
    }
  }
}