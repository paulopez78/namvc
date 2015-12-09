package main.java.namvc.controllers;

import main.java.namvc.framework.httpactions.NaMvcAction;
import main.java.namvc.framework.NaMvcContext;
import main.java.namvc.framework.NaMvcController;
import main.java.namvc.framework.NaMvcHttpContext;
import main.java.namvc.framework.httpactions.RenderAction;
import main.java.namvc.models.PageModel;
import main.java.namvc.views.PageView;

public class PageController extends NaMvcController {
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
