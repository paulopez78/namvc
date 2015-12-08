package namvc;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.Filter;
import namvc.controllers.NaMvcAction;
import namvc.controllers.UnauthorizedAction;

// https://leonardom.wordpress.com/2009/08/06/getting-parameters-from-httpexchange/
public class NaMvcAuthorizationFilter extends Filter {
    private String allowedRole;
    private NaMvcContext context;

    public NaMvcAuthorizationFilter(NaMvcContext context, String allowedRole)
    {
        this.context = context;
        this.allowedRole = allowedRole;
    }

    @Override
    public String description() {
        return "Authorization check for the current principal";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain)
        throws IOException {

        NaMvcPrincipal principal = (NaMvcPrincipal)exchange.getAttribute("principal");

        if (principal.isInRole(allowedRole))
        {
            chain.doFilter(exchange);
        }
        else
        {
            NaMvcAction action = new UnauthorizedAction();
            action.execute(exchange);
        }
    }
}