package namvc.framework.httpmodules;

import namvc.framework.*;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;

import java.io.IOException;

public class NaMvcHttpMethodModule implements NaMvcModule {
    private final NaMvcHttpSession session;
    private final NaMvcController controller;

    public NaMvcHttpMethodModule(NaMvcHttpSession session, NaMvcController controller) {
        this.controller = controller;
        this.session = session;
    }

    public boolean execute(NaMvcHttpContext httpContext) throws IOException {
        System.out.println(httpContext.getRequest().toString());

        String requestMethod = httpContext.getRequest().getMethod();

        NaMvcAction responseAction = null;

        if (requestMethod.equals("GET")) {
            responseAction = controller.getAction(session, httpContext);
        }

        if (requestMethod.equals("POST")) {
            responseAction = controller.postAction(session, httpContext);
        }

        if (responseAction == null) {
            throw new IOException("There is no response action for Method" + requestMethod);
        }

        responseAction.execute(httpContext.getResponse());
        return true;
    }
}
