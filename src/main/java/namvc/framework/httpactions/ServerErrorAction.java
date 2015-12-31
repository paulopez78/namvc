package namvc.framework.httpactions;

import java.net.HttpURLConnection;

public class ServerErrorAction extends RenderAction {
    public ServerErrorAction(Exception ex)
    {
        super("Internal Server error", HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
}
