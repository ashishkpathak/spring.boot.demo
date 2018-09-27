package au.com.pathak.springbootdemo.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

  @RequestMapping("/echo")
  public Map<String, String> echoRequestParams(@RequestBody String body,  HttpServletRequest request) throws IOException {

    Map<String, String> response = new LinkedHashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();

    while (headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      response.put(key, request.getHeader(key));

    }
    response.put("body", URLDecoder.decode(body));

    return response;
  }
}
