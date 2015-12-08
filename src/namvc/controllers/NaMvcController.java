package namvc.controllers;

import java.util.Map;

import namvc.NaMvcHttpContext;
import namvc.views.NaMvcView;
import namvc.NaMvcContext;

public abstract class NaMvcController{
  protected NaMvcView View;

  public NaMvcAction getAction(NaMvcContext context, NaMvcHttpContext httpContext){
    return new RenderAction(View.render(null));
  }

  public NaMvcAction postAction(NaMvcContext context, NaMvcHttpContext httpContext)
  {
    return new RedirectAction("/");
  }
}
