package namvc.views;

public class HomeView extends NaMvcView
{
  public String render(Object model) {

    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
      builder.append("<ul>");
        builder.append(li(a("/page1","Page 1")));
        builder.append(li(a("/page2","Page 2")));
        builder.append(li(a("/page3","Page 3")));
      builder.append("</ul>");
    builder.append("</html>");

    return builder.toString();
  }
}
