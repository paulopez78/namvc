package namvc;

import namvc.controllers.NaMvcAction;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import namvc.controllers.NaMvcController;

  public class  NaMvcHttpHandler implements HttpHandler {
    NaMvcController controller;
    NaMvcContext context;

    public NaMvcHttpHandler(NaMvcContext context, NaMvcController controller){
      this.controller = controller;
      this.context = context;
    }

    public void handle(HttpExchange exchange) throws IOException {
      System.out.println(exchange.getRequestMethod() + " " + exchange.getRequestURI().toString());

      String requestMethod = exchange.getRequestMethod();
      Map<String, Object> params = (Map<String, Object>)exchange.getAttribute("parameters");
      NaMvcPrincipal principal = (NaMvcPrincipal) exchange.getAttribute("principal");
      String sessionId = (String) exchange.getAttribute("sessionId");

      NaMvcHttpContext httpContext = new NaMvcHttpContext(principal, exchange.getRequestURI().getPath(), params, sessionId);
      NaMvcAction responseAction = null;

      if (requestMethod.equals("GET"))
      {
        responseAction = controller.getAction(context, httpContext);
      }

      if (requestMethod.equals("POST"))
      {
        responseAction = controller.postAction(context, httpContext);
      }

      if (responseAction == null) {
        throw new IOException("There is no response action for Method" + requestMethod);
      }

      responseAction.execute(exchange);
    }
}
