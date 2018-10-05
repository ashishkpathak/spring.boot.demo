package au.com.pathak.springbootdemo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import sun.util.resources.et.CalendarData_et;

@RestController
public class MockBillingController {

  private static Random random = new Random();

  @RequestMapping("/billingaccount/{account}")
  public BillSummary getCustomersViaFirstName(final @PathVariable String account) {

    BillSummary summary = new BillSummary();

    LocalDateTime dateTime = LocalDateTime.now();

    int dayOfMonth = dateTime.getDayOfMonth();
    LocalDate localDate = dateTime.toLocalDate();
    int monthEnd = localDate.lengthOfMonth();

    if (dayOfMonth != monthEnd) {
      dayOfMonth = dayOfMonth + (monthEnd - dayOfMonth) / 2;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm:ss a");
    localDate = localDate.plusDays((monthEnd - dayOfMonth) / 2);
    dateTime = localDate.atStartOfDay();
    List<CurrentBill> invoiceList = new ArrayList<CurrentBill>();

    summary.setAmountDue("$ 10");
    summary.setBillingAccountNumber(account);
    summary.setCurrentOverdueAmount("$ 10");
    summary.setDueDate(dateTime.format(formatter));

    summary.setInvoiceList(invoiceList);
    summary.setPaymentMethod(random.nextInt() % 2 == 0 ? PaymentMethod.DIRECT_DEBIT : PaymentMethod.OTHER);



    CurrentBill currentBill = new CurrentBill();
    currentBill.setAccountNum(account);
//    localDate = localDate.plusDays((monthEnd - dayOfMonth) / 2);
//    localDate.atStartOfDay().format(formatter);

    currentBill.setBillDate(localDate.minusMonths(1).atStartOfDay().format(formatter));
    currentBill.setBillNumber("10233322");
    currentBill.setTotalCharges("$ 100");

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(2).atStartOfDay().format(formatter));
    currentBill.setBillNumber("10214444");
    currentBill.setTotalCharges("$ 200");

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(3).atStartOfDay().format(formatter));
    currentBill.setBillNumber("932334");
    currentBill.setTotalCharges("$ 100");

    invoiceList.add(currentBill);

    return summary;

  }

}
