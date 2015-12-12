package namvc.framework;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;

public abstract class NaMvcController{
  protected NaMvcView View;

  public NaMvcAction getAction(NaMvcHttpSession session, NaMvcHttpContext httpContext){
    return new RenderAction(View.render(null));
  }

  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    return new RedirectAction("/");
  }
}
