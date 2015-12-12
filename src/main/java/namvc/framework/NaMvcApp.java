package namvc.framework;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.httpmodules.*;
import java.io.IOException;
import java.net.InetSocketAddress;

/*
 * a simple mvc application based on simple HttpServer
*/
public class NaMvcApp {
  private final HttpServer server;
  private final NaMvcHttpSession session;

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
    NaMvcHttpMethodModule httpModule = new NaMvcHttpMethodModule(session, controller);
    NaMvcStaticFilesModule staticFilesModule = new NaMvcStaticFilesModule();
    NaMvcAuthenticationModule authenticationModule = new NaMvcAuthenticationModule(session);
    NaMvcAuthorizationModule authorizationModule = new NaMvcAuthorizationModule(authorizationRole);

    HttpContext context = server.createContext(path, new NaMvcFilter(httpModule));
    context.getFilters().add(new NaMvcFilter(staticFilesModule));

    if (!anonymous(authorizationRole))
    {
      context.getFilters().add(new NaMvcFilter(authenticationModule));
      if (authorizationRequired(authorizationRole))
      {
        context.getFilters().add(new NaMvcFilter(authorizationModule));
      }
    }
  }

  public void start ()
  {
    server.setExecutor(null); // creates a default executor
    server.start();

    System.out.println("Listening on port " + this.server.getAddress().toString());
  }

  private boolean anonymous(String authorizationRole){
    return authorizationRole.equals("");
  }

  private boolean authorizationRequired(String authorizationRole){
    return !authorizationRole.equals("Any");
  }
}
