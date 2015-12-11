package namvc.framework;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;

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
