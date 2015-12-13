package namvc.framework.httpcontext;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.net.URLDecoder;

public class NaMvcHttpParameters {
    Map<String, Object> parameters = new HashMap<>();

    public NaMvcHttpParameters(String queryString, String postString) throws UnsupportedEncodingException {
        this.parseQuery(queryString, parameters);
        this.parseQuery(postString, parameters);
    }

    public String get(String key){
        return parameters.get(key).toString();
    }

    public boolean containsKey(String key)
    {
        return parameters.containsKey(key);
    }

     @SuppressWarnings("unchecked")
     private void parseQuery(String query, Map<String, Object> parameters)
         throws UnsupportedEncodingException {

         if (query != null) {
             if (!query.equals("")) {
                 String pairs[] = query.split("[&]");

                 for (String pair : pairs) {
                     String param[] = pair.split("[=]");

                     String key = null;
                     String value = null;
                     if (param.length > 0) {
                         key = URLDecoder.decode(param[0],
                                 System.getProperty("file.encoding"));
                     }

                     if (param.length > 1) {
                         value = URLDecoder.decode(param[1],
                                 System.getProperty("file.encoding"));
                     }

                     if (parameters.containsKey(key)) {
                         Object obj = parameters.get(key);
                         if (obj instanceof List<?>) {
                             List<String> values = (List<String>) obj;
                             values.add(value);
                         } else if (obj instanceof String) {
                             List<String> values = new ArrayList<String>();
                             values.add((String) obj);
                             values.add(value);
                             parameters.put(key, values);
                         }
                     } else {
                         parameters.put(key, value);
                     }
                 }
             }
         }
    }
}
