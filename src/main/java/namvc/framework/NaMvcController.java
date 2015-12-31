package namvc.framework;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.MvcHttpContext;

public abstract class NaMvcController{
  protected NaMvcView View;

  protected NaMvcController()
  {
    this.View = createDefaultView();
  }

  public NaMvcAction getAction(MvcHttpContext httpContext){
    return new RenderAction(View.render(null));
  }

  public NaMvcAction postAction(MvcHttpContext httpContext)
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
