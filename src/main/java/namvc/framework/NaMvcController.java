package main.java.namvc.framework;

import main.java.namvc.framework.httpactions.NaMvcAction;
import main.java.namvc.framework.httpactions.RedirectAction;
import main.java.namvc.framework.httpactions.RenderAction;

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
