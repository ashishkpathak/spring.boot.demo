package au.com.pathak.springbootdemo.model;

import java.io.Serializable;

public class BillDetail implements Serializable {

  private String format;
  private byte[] output;

  public BillDetail() {

  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public byte[] getOutput() {
    return output;
  }

  public void setOutput(byte[] output) {
    this.output = output;
  }
}
