package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;

public abstract class NaMvcAction
{
  public abstract void execute(HttpExchange t);
}
