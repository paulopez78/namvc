package namvc.controllers;

import namvc.NaMvcContext;
import namvc.NaMvcHttpContext;
import namvc.models.PageModel;
import namvc.views.PageView;

public class PageController extends NaMvcController{
  public PageController()
  {
    this.View = new PageView();
  }

  @Override
  public NaMvcAction getAction(NaMvcContext context, NaMvcHttpContext httpContext)
  {
    PageModel model = new PageModel(httpContext.getPrincipal().getUserName(), httpContext.getPath());
    return new RenderAction(View.render(model));
  }
}
