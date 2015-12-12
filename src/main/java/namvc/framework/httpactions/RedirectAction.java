package namvc.framework.httpactions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import namvc.framework.httpcontext.NaMvcHttpResponse;

public class RedirectAction extends NaMvcAction {
    private String path;

    public RedirectAction(String path)
    {
      this.path = path;
    }

    @Override
    public void execute(NaMvcHttpResponse response) throws IOException {
        List<String> values = new ArrayList<>();
        values.add(this.path);
        response.addHeader("Location", values);
        response.sendHttpCode(HttpURLConnection.HTTP_MOVED_PERM, -1);
    }
  }
