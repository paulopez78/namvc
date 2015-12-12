package namvc.framework.httpmodules;

import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.NotFoundAction;

import java.io.IOException;

public class NaMvcStaticFilesModule implements NaMvcModule {
    public boolean execute(NaMvcHttpContext httpContext) throws IOException {
        // Returns 404 for every requested static file detected
        boolean detectedStaticFile = httpContext.getRequest().getPath().contains(".");
        if (detectedStaticFile) {
            NaMvcAction notFoundAction = new NotFoundAction();
            notFoundAction.execute(httpContext.getResponse());
            return false;
        }
        else {
            return true;
        }
    }

}
