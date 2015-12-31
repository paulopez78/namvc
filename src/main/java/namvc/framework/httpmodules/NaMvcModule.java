package namvc.framework.httpmodules;

import namvc.framework.httpcontext.MvcHttpContext;
import java.io.IOException;

public interface NaMvcModule {
    boolean execute(MvcHttpContext httpContext) throws IOException;
}
