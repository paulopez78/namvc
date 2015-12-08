package namvc.views;

public abstract class NaMvcView
{
  public abstract String render(Object model);

  protected String a(String route, String label)
  {
    return "<a href="+route+">"+label+"</a>";
  }

  protected String li(String content)
  {
    return "<li>"+ content +"</li>";
  }

  protected String input(String content, String id, boolean password)
  {
    if (password)
    {
      return content + "<input type='password' name='"+ id +"'></input>";
    }
    else
    {
      return content + "<input type='text' name='"+ id +"'></input>";
    }
  }

  protected String submit(String content)
  {
    return "<button>"+ content + "</button>";
  }
}
