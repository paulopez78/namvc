package main.java.namvc.framework.httpactions;

import main.java.namvc.framework.httpactions.RenderAction;

import java.net.HttpURLConnection;

public class NotFoundAction extends RenderAction {
    public NotFoundAction()
    {
        super("Not Found", HttpURLConnection.HTTP_NOT_FOUND);
    }
}
