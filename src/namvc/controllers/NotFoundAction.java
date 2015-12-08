package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;

public class NotFoundAction extends RenderAction {
    public NotFoundAction()
    {
        super("Not Found", HttpURLConnection.HTTP_NOT_FOUND);
    }
}
