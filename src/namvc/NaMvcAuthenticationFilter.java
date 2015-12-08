package namvc;

import namvc.controllers.NaMvcController;
import namvc.controllers.RedirectAction;
import namvc.controllers.NaMvcAction;

import java.io.IOException;
import java.io.OutputStream;
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

        String sessionId = getSessionId(exchange);
        if (sessionId.equals(""))
        {
          // redirect to login page with the current path /login?url=/page1
          NaMvcAction redirect = new RedirectAction("/login?redirectUrl="+ exchange.getRequestURI().getPath());
          redirect.execute(exchange);
        }
        else
        {
            NaMvcPrincipal currentPrincipal = context.getSession().getPrincipal(sessionId);
            exchange.setAttribute("principal", currentPrincipal);
            exchange.setAttribute("sessionId", sessionId);

            chain.doFilter(exchange);
        }
    }

    private String getSessionId(HttpExchange exchange)
    {
        // some small sanity check
        Headers headers = exchange.getRequestHeaders();
        List<String> cookies = headers.get("Cookie");

        if (cookies != null)
        {
            String sessionId = cookies.stream()
                    .filter(cookie -> cookie.contains("NAMVCSESSIONID"))
                    .findFirst()
                    .get();

            // parse session Id
            return sessionId.split("=")[1];
        }

        return "";
    }
}
