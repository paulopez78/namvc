package main.java.namvc.framework;

import main.java.namvc.framework.httpactions.NaMvcAction;
import main.java.namvc.framework.httpactions.RedirectAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.Filter;

// https://leonardom.wordpress.com/2009/08/06/getting-parameters-from-httpexchange/
public class NaMvcAuthenticationFilter extends Filter {

    private NaMvcContext context;

    public NaMvcAuthenticationFilter(NaMvcContext context)
    {
        this.context = context;
    }

    @Override
    public String description() {
        return "Authentication check by session cookie, redirects to login page if fails";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain)
        throws IOException {

        String cookie = getSesionCookie(exchange);
        if (cookie.equals(""))
        {
          // redirect to login page with the current path /login?url=/page1
          NaMvcAction redirect = new RedirectAction("/login?redirectUrl="+ exchange.getRequestURI().getPath());
          redirect.execute(exchange);
        }
        else
        {
            String sessionId = getSessionId(cookie);
            NaMvcPrincipal currentPrincipal = context.getSession().getPrincipal(sessionId);
            exchange.setAttribute("principal", currentPrincipal);
            exchange.setAttribute("sessionId", sessionId);

            sendCookieSession(exchange, cookie);
            chain.doFilter(exchange);
        }
    }

    private void sendCookieSession(HttpExchange exchange, String cookie)
    {
        Headers respHeaders = exchange.getResponseHeaders();
        List<String> values = new ArrayList<>();
        values.add(cookie +";max-age=" + this.context.getSession().getTimeout()+ ";version=1;Path=/;HttpOnly");
        respHeaders.put("Set-Cookie", values);
    }

    private String getSesionCookie(HttpExchange exchange)
    {
        // some small sanity check
        Headers headers = exchange.getRequestHeaders();
        List<String> cookies = headers.get("Cookie");
        String cookieSession = "";

        if (cookies != null)
        {
             cookieSession = cookies.stream()
                    .filter(cookie -> cookie.contains(this.context.getSession().COOKIE_NAME))
                    .findFirst()
                    .get();

        }

        return cookieSession;
    }

    private String getSessionId(String cookieSession)
    {
        String sessionId = "";
        String[] cookiePart = cookieSession.split("=");
        if (cookiePart.length > 0)
        {
            sessionId = cookiePart[1];
        }

        return sessionId;
    }
}
