package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpURLConnection;

public class NotFoundAction extends NaMvcAction {
    @Override
    public void execute(HttpExchange t)
    {
        try
        {
            t.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, -1);
            t.close();
        }
        catch(Exception ex)
        {

        }
    }
}
