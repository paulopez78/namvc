package namvc.framework;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;

public abstract class NaMvcController{
  protected NaMvcView View;

  protected NaMvcController()
  {
    this.View = createDefaultView();
  }

  public NaMvcAction getAction(NaMvcHttpSession session, NaMvcHttpContext httpContext){
    return new RenderAction(View.render(null));
  }

  public NaMvcAction postAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    return new RedirectAction("/");
  }

  private NaMvcView createDefaultView()
  {
    try
    {
      String defaultViewClassConvention =
              String.format("%s.%s",
              this.getClass().getPackage().getName().replace("controllers","views"),
              this.getClass().getSimpleName().replace("Controller","View"));

      return (NaMvcView) Class.forName(defaultViewClassConvention).newInstance();
    }
    catch (Exception ex)
    {
      return null;
    }
  }
}
