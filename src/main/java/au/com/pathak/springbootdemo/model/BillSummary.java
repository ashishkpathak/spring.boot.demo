package au.com.pathak.springbootdemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BillSummary implements Serializable {

  private PaymentMethod paymentMethod;
  private Money amountDue;
  private Money currentOverdueAmount;
  private String billingAccountNumber;
  private String dueDate;

  private List<CurrentBill> invoiceList = new ArrayList<CurrentBill>();

  /**
   * { "paymentMethod": "OTHER", "amountDue": "$0.00", "currentOverdueAmount": "$0.00", "billingAccountNumber":
   * "159191683", "dueDate":"6 October 2018",
   * 
   * "invoiceList": [ { "billNumber": "222628501", "billDate": "Sep 5, 2018 12:00:00 AM", "totalCharges": "$80.34",
   * "accountNum": "159191683"
   * 
   * }, { "billNumber": "219761734", "billDate": "Aug 6, 2018 12:00:00 AM", "totalCharges": "$80.00", "accountNum":
   * "159191683"
   * 
   * }, { "billNumber": "864650787", "billDate": "Jul 4, 2018 12:00:00 AM", "totalCharges": "$79.99", "accountNum":
   * "86994868000184"
   * 
   * } ] }
   */

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Money getAmountDue() {
    return amountDue;
  }

  public void setAmountDue(Money amountDue) {
    this.amountDue = amountDue;
  }

  public Money getCurrentOverdueAmount() {
    return currentOverdueAmount;
  }

  public void setCurrentOverdueAmount(Money currentOverdueAmount) {
    this.currentOverdueAmount = currentOverdueAmount;
  }

  public String getBillingAccountNumber() {
    return billingAccountNumber;
  }

  public void setBillingAccountNumber(String billingAccountNumber) {
    this.billingAccountNumber = billingAccountNumber;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public List<CurrentBill> getInvoiceList() {
    return invoiceList;
  }

  public void setInvoiceList(List<CurrentBill> invoiceList) {
    this.invoiceList = invoiceList;
  }
}
