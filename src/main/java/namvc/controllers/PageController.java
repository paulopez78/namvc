package namvc.controllers;

import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.NaMvcController;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpactions.RenderAction;
import namvc.models.PageModel;
import namvc.views.PageView;

public class PageController extends NaMvcController {
  public PageController()
  {
    this.View = new PageView();
  }

  @Override
  public NaMvcAction getAction(NaMvcHttpSession session, NaMvcHttpContext httpContext)
  {
    PageModel model = new PageModel(
            httpContext.getPrincipal().getUserName(),
            httpContext.getRequest().getPath());

    return new RenderAction(View.render(model));
  }
}
