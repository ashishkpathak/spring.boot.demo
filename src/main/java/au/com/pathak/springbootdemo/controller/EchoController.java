package au.com.pathak.springbootdemo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import au.com.pathak.springbootdemo.model.EchoResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

  @RequestMapping("/echo")
  public EchoResponse echoRequestParams(@RequestBody(required = false) String body, HttpServletRequest request) throws IOException {

    final EchoResponse response = new EchoResponse();
    Enumeration<String> headerNames = request.getHeaderNames();

    while (headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      List<String> strings = response.getHeaders().get(key);
      if(CollectionUtils.isEmpty(strings)) {
        response.getHeaders().put(key, Arrays.asList(request.getHeader(key)));
      } else {
        List<String> newList = new ArrayList<String>();
        newList.addAll(strings);
        newList.add(request.getHeader(key));
        response.getHeaders().put(key,newList);
      }

    }

    if(StringUtils.isNotBlank(body)) {
      response.setBody(body);
    }

    String queryString = request.getQueryString();
    if(queryString != null){
      response.setQuery(queryString);
    }

    return response;
  }
}
