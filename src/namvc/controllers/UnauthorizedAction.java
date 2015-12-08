package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpURLConnection;

public class UnauthorizedAction extends NaMvcAction {
    @Override
    public void execute(HttpExchange t)
    {
        try
        {
            t.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, -1);
            t.close();
        }
        catch(Exception ex)
        {

        }
    }
}
