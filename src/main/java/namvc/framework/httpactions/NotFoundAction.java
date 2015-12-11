package namvc.framework.httpactions;

import java.net.HttpURLConnection;

public class NotFoundAction extends RenderAction {
    public NotFoundAction()
    {
        super("Not Found", HttpURLConnection.HTTP_NOT_FOUND);
    }
}
