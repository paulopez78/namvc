package namvc.framework.httpmodules;

import namvc.framework.httpcontext.NaMvcHttpContext;
import java.io.IOException;

public interface NaMvcModule {
    boolean execute(NaMvcHttpContext httpContext) throws IOException;
}
