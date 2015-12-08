package namvc.controllers;

import namvc.NaMvcContext;
import namvc.NaMvcHttpContext;
import namvc.NaMvcPrincipal;

import java.util.Map;

public class LogoutController extends NaMvcController{

  @Override
  public NaMvcAction postAction(NaMvcContext context, NaMvcHttpContext httpContext)
  {
    try
    {
      context.getSession().kill(httpContext.getSessionId());
      return new SetSessionAction(httpContext.getSessionId(), 0, "/login");

    }
    catch(Exception ex)
    {
      return new RenderAction(View.render("Login error"));
    }
  }
}