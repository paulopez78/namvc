package namvc.framework.httpmodules;

import namvc.framework.*;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.ServerErrorAction;
import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpcontext.MvcHttpResponse;

import java.io.IOException;

public class NaMvcHttpMethodModule implements NaMvcModule {
    private final NaMvcController controller;

    public NaMvcHttpMethodModule(NaMvcController controller) {
        this.controller = controller;
    }

    public boolean execute(MvcHttpContext httpContext) {
        System.out.println(httpContext.getRequest().toString());

        String requestMethod = httpContext.getRequest().getMethod();
        NaMvcAction responseAction = null;

        try {

            if (requestMethod.equals("GET")) {
            responseAction = controller.getAction(httpContext);
        }

        if (requestMethod.equals("POST")) {
            responseAction = controller.postAction(httpContext);
        }

        if (responseAction == null) {
            throw new IOException("There is no response action for Method" + requestMethod);
        }
            responseAction.execute(httpContext.getResponse(), httpContext.getSessionInfo());
        }
        catch (IOException e) {
            handle(httpContext.getResponse(), e);
            return false;
        }

        return true;
    }

    private void handle(MvcHttpResponse response, Exception ex)
    {
        NaMvcAction serverError = new ServerErrorAction(ex);
        try {
            serverError.execute(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}