package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class NaMvcAction
{
  public abstract void execute(HttpExchange t) throws IOException;
}
