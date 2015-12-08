package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class UnauthorizedAction extends RenderAction {
    public UnauthorizedAction()
    {
        super("Not Authorized", HttpURLConnection.HTTP_UNAUTHORIZED);
    }
}
