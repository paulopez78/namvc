package namvc.views;

import namvc.models.PageModel;

import java.util.Map;

public class PageView extends NaMvcView
{
  public String render(Object model) {
      PageModel pageModel = (PageModel)model;

      StringBuilder builder = new StringBuilder();
      builder.append("<html>");

      if (pageModel != null)
      {
          builder.append("<p>" + pageModel.getUser() + "</p>");
          builder.append("<p>" + pageModel.getPageName() + "</p>");
      }

      builder.append("<form action='/logout' method='post'>");
          builder.append(submit("Logout"));
      builder.append("</form>");
      builder.append("</html>");
      return builder.toString();
  }
}
