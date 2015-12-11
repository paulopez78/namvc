package namvc.framework;


import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.NotFoundAction;

import java.io.IOException;

public class NaMvcStaticFilesFilter extends Filter {
    @Override
    public String description() {
        return "Returns static files";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain)
            throws IOException {

        // Returns 404 for every requested static file detected
        boolean detectedStaticFile = exchange.getRequestURI().getPath().contains(".");
        if (detectedStaticFile) {
            NaMvcAction notFoundAction = new NotFoundAction();
            notFoundAction.execute(exchange);
        }
        else {
            chain.doFilter(exchange);
        }
    }

}
