package namvc.framework.httpmodules;

import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;

import java.io.IOException;

public class NaMvcAuthenticationModule implements NaMvcModule {

    public boolean execute(MvcHttpContext httpContext) throws IOException {
        if (httpContext.authenticated()) {
            return true;
        }
        else
        {
            // redirect to login page with the current path /login?url=/page1
            NaMvcAction redirect = new RedirectAction
                    ("/login?redirectUrl="+ httpContext.getRequest().getPath());
            redirect.execute(httpContext.getResponse());
            return false;

        }
    }
}
