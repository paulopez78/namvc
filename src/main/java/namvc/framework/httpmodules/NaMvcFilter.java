package namvc.framework.httpmodules;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import namvc.framework.httpcontext.NaMvcHttpContext;

import java.io.IOException;

public class NaMvcFilter extends Filter implements HttpHandler{

    private final NaMvcModule module;

    public NaMvcFilter(NaMvcModule module)
    {
        this.module = module;
    }

    @Override
    public String description() {
        return "Generic Filter and HttpHandler for executing NaMvcModule objects";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        if (executeModule(exchange))
        {
            chain.doFilter(exchange);
        }
    }

    public void handle(HttpExchange exchange) throws IOException {
        executeModule(exchange);
        setHttpContext(exchange, null);
    }

    private boolean executeModule(HttpExchange exchange) {
        boolean result = false;
        try
        {
            NaMvcHttpContext httpContext = getHttpContext(exchange);
            result = module.execute(httpContext);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            return result;
        }
    }

    private NaMvcHttpContext getHttpContext(HttpExchange exchange) throws IOException {
        String key = "namvcHttpContext";
        if (exchange.getAttribute(key) == null)
        {
            exchange.setAttribute(key, new NaMvcHttpContext(exchange));
        }

        return (NaMvcHttpContext)exchange.getAttribute(key);
    }

    private void setHttpContext(HttpExchange exchange, NaMvcHttpContext context)
    {
        String key = "namvcHttpContext";
        exchange.setAttribute(key, context);
    }
}

