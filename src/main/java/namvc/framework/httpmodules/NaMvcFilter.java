package namvc.framework.httpmodules;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.ServerErrorAction;
import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpcontext.MvcHttpSession;
import namvc.framework.httpcontext.NaMvcHttpContext;

public class NaMvcFilter extends Filter implements HttpHandler{

    private final NaMvcModule module;
    private final boolean bootstrap;
    private final MvcHttpSession session;

    public NaMvcFilter(NaMvcModule module, MvcHttpSession session, boolean bootstrap)
    {
        this.module = module;
        this.bootstrap = bootstrap;
        this.session = session;
    }

    public NaMvcFilter(NaMvcModule module, MvcHttpSession session)

    {
        this(module, session, false);
    }

    @Override
    public String description() {
        return "Generic Filter and HttpHandler for executing NaMvcModule objects";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain){
        if (executeModule(exchange))
        {
            try {
                chain.doFilter(exchange);
            } catch (Exception ex) {
                handle(exchange, ex);
            }
        }
    }

    public void handle(HttpExchange exchange) {
        executeModule(exchange);
    }

    private boolean executeModule(HttpExchange exchange) {
        boolean result = false;
        try
        {
            MvcHttpContext httpContext = getHttpContext(exchange);
            result = module.execute(httpContext);
        }
        catch (Exception ex)
        {
            handle(exchange, ex);
        }
        finally
        {
            return result;
        }
    }

    private void handle(HttpExchange exchange, Exception ex)
    {
        NaMvcAction serverError = new ServerErrorAction(ex);
        MvcHttpContext httpContext = getHttpContext(exchange);

        try {
            serverError.execute(httpContext.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MvcHttpContext getHttpContext(HttpExchange exchange) {
        String key = "namvcHttpContext";

        if (bootstrap)
        {
            exchange.setAttribute(key, new NaMvcHttpContext(exchange, session));
        }

        return (MvcHttpContext)exchange.getAttribute(key);
    }
}

