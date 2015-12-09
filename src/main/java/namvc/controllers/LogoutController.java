package main.java.namvc.controllers;

import main.java.namvc.framework.httpactions.NaMvcAction;
import main.java.namvc.framework.NaMvcContext;
import main.java.namvc.framework.NaMvcController;
import main.java.namvc.framework.NaMvcHttpContext;
import main.java.namvc.framework.httpactions.RenderAction;
import main.java.namvc.framework.httpactions.SetSessionAction;

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