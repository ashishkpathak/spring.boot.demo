package au.com.pathak.springbootdemo.model;

import java.io.Serializable;

public class CurrentBill implements Serializable {

  /**
   * "billNumber": "222628501", "billDate": "Sep 5, 2018 12:00:00 AM", "totalCharges": "$80.34", "accountNum": "159191683"
   */
  private String billNumber;
  private String billDate;
  private Money totalCharges;
  private String accountNum;

  public String getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(String billNumber) {
    this.billNumber = billNumber;
  }

  public String getBillDate() {
    return billDate;
  }

  public void setBillDate(String billDate) {
    this.billDate = billDate;
  }

  public Money getTotalCharges() {
    return totalCharges;
  }

  public void setTotalCharges(Money totalCharges) {
    this.totalCharges = totalCharges;
  }

  public String getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(String accountNum) {
    this.accountNum = accountNum;
  }
}
