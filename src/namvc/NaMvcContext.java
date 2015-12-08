package namvc;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import namvc.controllers.NaMvcController;

public interface NaMvcContext  {
  NaMvcHttpSession getSession();
  Users getUsers();
}
