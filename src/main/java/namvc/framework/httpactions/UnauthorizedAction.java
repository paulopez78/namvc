package main.java.namvc.framework.httpactions;

import java.net.HttpURLConnection;

public class UnauthorizedAction extends RenderAction {
    public UnauthorizedAction()
    {
        super("Not Authorized", HttpURLConnection.HTTP_UNAUTHORIZED);
    }
}
