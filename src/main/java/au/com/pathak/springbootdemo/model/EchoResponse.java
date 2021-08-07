package au.com.pathak.springbootdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EchoResponse {

  private String body;
  private Map<String, List<String>> headers = new HashMap<>();
  private String query = new String();

}
