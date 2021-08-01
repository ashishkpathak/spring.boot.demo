package au.com.pathak.springbootdemo.controller;

import java.util.Map;

import au.com.pathak.springbootdemo.model.LoginDetail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public LoginDetail authenticate(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = false) String password) {

    LoginDetail detail = new LoginDetail(userName);
    Map<String, String> attributes = detail.getAttributes();
    attributes.put("G1", "G00001");
    attributes.put("G10", "G00301");
    attributes.put("G20", "G00201");
    attributes.put("G30", "G00100");

    return detail;

  }
}
