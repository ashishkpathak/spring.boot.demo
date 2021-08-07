package au.com.pathak.springbootdemo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginDetail implements Serializable {

  private String userName;
  private Map<String, String> attributes = new HashMap<>();

  public LoginDetail(String userName) {
    this.userName = userName;
  }

  public Map<String, String> getAttributes() {
    return attributes;

  }

  @Override
  public String toString() {
    return "LoginDetail{" + "userName='" + userName + '\'' + ", attributes=" + attributes + '}';
  }
}
