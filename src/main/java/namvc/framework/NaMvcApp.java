package namvc.framework;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import namvc.framework.httpcontext.MvcHttpSession;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpmodules.*;
import java.io.IOException;
import java.net.InetSocketAddress;

/*
 * a simple mvc application based on simple HttpServer
*/
public class NaMvcApp {
  private final HttpServer server;
  private final MvcHttpSession session;

  public NaMvcApp(int port, int sessionTimeOut) throws IOException
  {
    this.server = HttpServer.create(new InetSocketAddress(port), 0);
    this.session = new NaMvcHttpSession(sessionTimeOut);
  }

  public void route(String path, NaMvcController controller)
  {
    route(path, controller, "");
  }

  public void route(String path, NaMvcController controller, String authorizationRole)
  {
    NaMvcModule httpModule = new NaMvcHttpMethodModule(controller);
    NaMvcModule staticFilesModule = new NaMvcStaticFilesModule();
    NaMvcModule authenticationModule = new NaMvcAuthenticationModule();
    NaMvcModule authorizationModule = new NaMvcAuthorizationModule(authorizationRole);

    HttpContext context = server.createContext(path, createFilter(httpModule));
    context.getFilters().add(createBootStrapFilter(staticFilesModule));

    if (!anonymous(authorizationRole))
    {
      context.getFilters().add(createFilter(authenticationModule));
      if (authorizationRequired(authorizationRole))
      {
        context.getFilters().add(createFilter(authorizationModule));
      }
    }
  }

  public void start ()
  {
    server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
    server.start();

    System.out.println("Listening on port " + this.server.getAddress().toString());
  }

  private boolean anonymous(String authorizationRole){
    return authorizationRole.equals("");
  }

  private boolean authorizationRequired(String authorizationRole){
    return !authorizationRole.equals("Any");
  }

  private NaMvcFilter createFilter(NaMvcModule module)
  {
    return new NaMvcFilter(module, session);
  }

  private NaMvcFilter createBootStrapFilter(NaMvcModule module)
  {
    return new NaMvcFilter(module, session, true);
  }
}