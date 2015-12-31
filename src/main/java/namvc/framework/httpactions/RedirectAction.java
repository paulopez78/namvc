package namvc.framework.httpactions;

import namvc.framework.httpcontext.MvcHttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RedirectAction extends NaMvcAction {
    private String path;

    public RedirectAction(String path)
    {
      this.path = path;
    }

    @Override
    public void execute(MvcHttpResponse response) throws IOException {
        List<String> values = new ArrayList<>();
        values.add(this.path);
        response.addHeader("Location", values);
        response.sendHttpCode(HttpURLConnection.HTTP_MOVED_PERM,-1);
    }
  }
