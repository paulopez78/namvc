package main.java.namvc.models;


public class PageModel {
    private String user;
    private String pageName;

    public PageModel(String user, String pageName)
    {
        this.user = user;
        this.pageName = pageName;
    }

    public String getUser() { return user;}
    public String getPageName() { return pageName; }
}
