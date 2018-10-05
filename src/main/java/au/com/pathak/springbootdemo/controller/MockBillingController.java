package au.com.pathak.springbootdemo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import au.com.pathak.springbootdemo.model.BillSummary;
import au.com.pathak.springbootdemo.model.CurrentBill;
import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.model.PaymentMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockBillingController {

  private static Random random =new Random();

  @RequestMapping("/billingaccount/{account}")
  public BillSummary getCustomersViaFirstName(final @PathVariable String account) {


    Set<CurrentBill> invoiceList =new HashSet<CurrentBill>();
    CurrentBill currentBill =new CurrentBill();
    currentBill.setAccountNum(account);
    currentBill.setBillDate("10 Sept, 2018 12:00:00 AM");
    currentBill.setBillNumber("10233322");
    currentBill.setTotalCharges("$ 100");

    invoiceList.add(currentBill);
    currentBill =new CurrentBill();
    currentBill.setAccountNum(account);
    currentBill.setBillDate("10 Aug, 2018 12:00:00 AM");
    currentBill.setBillNumber("10214444");
    currentBill.setTotalCharges("$ 200");

    invoiceList.add(currentBill);
    currentBill =new CurrentBill();
    currentBill.setAccountNum(account);
    currentBill.setBillDate("10 Jul, 2018 12:00:00 AM");
    currentBill.setBillNumber("932334");
    currentBill.setTotalCharges("$ 100");

    invoiceList.add(currentBill);

    BillSummary summary =new BillSummary();
    summary.setAmountDue("$ 10");
    summary.setBillingAccountNumber(account);
    summary.setCurrentOverdueAmount("$ 10");
    summary.setDueDate("10 Oct, 2018 12:00:00 AM");
    summary.setInvoiceList(invoiceList);
    summary.setPaymentMethod(random.nextInt()%2==0?PaymentMethod.DIRECT_DEBIT:PaymentMethod.OTHER);
    return summary;

  }


}
