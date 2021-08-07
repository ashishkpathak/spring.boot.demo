package au.com.pathak.springbootdemo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.com.pathak.springbootdemo.model.BillDetail;
import au.com.pathak.springbootdemo.model.BillSummary;
import au.com.pathak.springbootdemo.model.CurrentBill;
import au.com.pathak.springbootdemo.model.Money;
import au.com.pathak.springbootdemo.model.PaymentMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockBillingController implements BillingControllerIF {

  private static Random random = new Random();
  @Autowired
  private ResourceLoader resourceLoader;

  // @RequestMapping("/billingaccount/{account}")
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

    int amount = random.nextInt(1000);
    summary.setAmountDue(new Money(String.valueOf(amount)));
    summary.setBillingAccountNumber(account);
    summary.setCurrentOverdueAmount(new Money(String.valueOf(amount)));
    summary.setDueDate(dateTime.format(formatter));

    summary.setInvoiceList(invoiceList);
    summary.setPaymentMethod(random.nextInt() % 2 == 0 ? PaymentMethod.DIRECT_DEBIT : PaymentMethod.OTHER);

    CurrentBill currentBill = new CurrentBill();
    currentBill.setAccountNum(account);
    // localDate = localDate.plusDays((monthEnd - dayOfMonth) / 2);
    // localDate.atStartOfDay().format(formatter);

    currentBill.setBillDate(localDate.minusMonths(1).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(1000000)));
    currentBill.setTotalCharges(new Money(String.valueOf(random.nextInt(1000))));

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(2).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(100000)));
    currentBill.setTotalCharges(new Money(String.valueOf(random.nextInt(1000))));

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(3).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(10000)));
    currentBill.setTotalCharges(new Money(String.valueOf(random.nextInt(1000))));

    invoiceList.add(currentBill);

    return summary;

  }

  @Override
  public BillDetail getBillDetails(String account, String invoiceid, String outputFormat) throws Exception {

    BillDetail billDetail = new BillDetail();
    if (StringUtils.equalsIgnoreCase("PDF", outputFormat)) {

      byte[] buffer = new byte[0x10000];

      ByteArrayOutputStream baos = new ByteArrayOutputStream(0x1000);
      // BufferedInputStream bis = null;

      // InputStream systemResourceAsStream = ;
      // System.out.println(systemResourceAsStream);

      Resource resource = resourceLoader.getResource("classpath:invoices/my-bill.pdf");

      try (BufferedInputStream bis = new BufferedInputStream(resource.getInputStream())) {

        int bRead = -1;
        while ((bRead = bis.read(buffer)) != -1) {
          baos.write(buffer, 0, bRead);
        }

      } finally {

      }

      billDetail.setOutput(baos.toByteArray());
      baos.close();

      // billDetail.setOutput();
    }

    billDetail.setFormat(outputFormat);
    return billDetail;
  }

}
