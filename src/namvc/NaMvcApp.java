package namvc;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import namvc.controllers.NaMvcController;

import java.io.IOException;
import java.net.InetSocketAddress;

/*
 * a simple mvc application based on simple HttpServer
*/
public class NaMvcApp implements NaMvcContext {
  private HttpServer server;
  private NaMvcHttpSession session;
  private Users users;

  public NaMvcApp(int port, int sessionTimeOut) throws IOException
  {
    this.server = HttpServer.create(new InetSocketAddress(port), 0);
    this.session = new NaMvcHttpSession(sessionTimeOut);
    this.users = new Users();
  }

  public NaMvcHttpSession getSession(){
    return this.session;
  }

  public Users getUsers(){
    return this.users;
  }

  public void route(String path, NaMvcController controller)
  {
    route(path, controller, "");
  }

  public void route(String path, NaMvcController controller, String authorizationRole)
  {
    HttpContext context = server.createContext(path, new NaMvcHttpHandler(this, controller));

    context.getFilters().add(new NaMvcStaticFilesFilter());
    context.getFilters().add(new NaMvcParameterFilter());

    if (!authorizationRole.equals(""))
    {
      context.getFilters().add(new NaMvcAuthenticationFilter(this));
      if (!authorizationRole.equals("Any"))
      {
        context.getFilters().add(new NaMvcAuthorizationFilter(this, authorizationRole));
      }
    }
  }

  public void start ()
  {
    server.setExecutor(null); // creates a default executor
    server.start();

    this.users.create();
    System.out.println("Listening on port " + this.server.getAddress().toString());
  }
}
