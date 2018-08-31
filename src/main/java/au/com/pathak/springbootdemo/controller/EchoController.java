package au.com.pathak.springbootdemo.controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {



  @RequestMapping("/echo")
  public Map<String,String> echoRequestParams(HttpServletRequest request) {

    Map<String, String> response = new LinkedHashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();

    while(headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      response.put(key,request.getHeader(key));

    }

    return response;
  }
}
