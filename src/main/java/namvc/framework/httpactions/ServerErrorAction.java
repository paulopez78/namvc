package namvc.framework.httpactions;

import java.net.HttpURLConnection;

public class ServerErrorAction extends RenderAction {
    public ServerErrorAction()
    {
        super("Server error", HttpURLConnection.HTTP_SERVER_ERROR);
    }
}
