package namvc.framework.httpactions;

import namvc.framework.httpcontext.NaMvcHttpResponse;

import java.io.IOException;

public abstract class NaMvcAction
{
  public abstract void execute(NaMvcHttpResponse response) throws IOException;
}
