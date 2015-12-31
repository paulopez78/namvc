package namvc.framework.httpmodules;

import java.io.IOException;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.UnauthorizedAction;
import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpContext;

public class NaMvcAuthorizationModule implements NaMvcModule {
    private final String allowedRole;

    public NaMvcAuthorizationModule(String allowedRole)
    {
        this.allowedRole = allowedRole;
    }

    public boolean execute(MvcHttpContext httpContext) throws IOException {

        if (httpContext.getPrincipal().isInRole(allowedRole))
        {
            return true;
        }
        else
        {
            NaMvcAction action = new UnauthorizedAction();
            action.execute(httpContext.getResponse());
            return false;
        }
    }
}
