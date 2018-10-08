package au.com.pathak.springbootdemo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import au.com.pathak.springbootdemo.model.BillDetail;
import au.com.pathak.springbootdemo.model.BillSummary;
import au.com.pathak.springbootdemo.model.CurrentBill;
import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.model.PaymentMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.resources.et.CalendarData_et;

@RestController
public class MockBillingController implements BillingControllerIF {


  @Autowired
  private ResourceLoader resourceLoader;

  private static Random random = new Random();

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
    summary.setAmountDue("$ " + amount);
    summary.setBillingAccountNumber(account);
    summary.setCurrentOverdueAmount("$ " + amount);
    summary.setDueDate(dateTime.format(formatter));

    summary.setInvoiceList(invoiceList);
    summary.setPaymentMethod(random.nextInt() % 2 == 0 ? PaymentMethod.DIRECT_DEBIT : PaymentMethod.OTHER);

    CurrentBill currentBill = new CurrentBill();
    currentBill.setAccountNum(account);
    // localDate = localDate.plusDays((monthEnd - dayOfMonth) / 2);
    // localDate.atStartOfDay().format(formatter);

    currentBill.setBillDate(localDate.minusMonths(1).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(1000000)));
    currentBill.setTotalCharges("$ " + random.nextInt(1000));

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(2).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(100000)));
    currentBill.setTotalCharges("$ " + random.nextInt(1000));

    invoiceList.add(currentBill);
    currentBill = new CurrentBill();
    currentBill.setAccountNum(account);

    currentBill.setBillDate(localDate.minusMonths(3).atStartOfDay().format(formatter));
    currentBill.setBillNumber(String.valueOf(random.nextInt(10000)));
    currentBill.setTotalCharges("$ " + random.nextInt(1000));

    invoiceList.add(currentBill);

    return summary;

  }

  @Override
  public BillDetail getBillDetails(String account, String invoiceid, String outputFormat) throws Exception {

    BillDetail billDetail = new BillDetail();
    if (StringUtils.equalsIgnoreCase("PDF", outputFormat)) {

      byte[] buffer = new byte[0x10000];

      ByteArrayOutputStream baos  =new ByteArrayOutputStream(0x1000);
//      BufferedInputStream bis = null;

//      InputStream systemResourceAsStream = ;
//      System.out.println(systemResourceAsStream);

      Resource resource = resourceLoader.getResource("classpath:invoices/my-bill.pdf");


      try(BufferedInputStream bis = new BufferedInputStream(resource.getInputStream())) {

        int bRead = -1;
        while((bRead = bis.read(buffer))!=-1) {
          baos.write(buffer,0,bRead);
        }


      }finally {

      }



      billDetail.setFormat(outputFormat);
      billDetail.setOutput(baos.toByteArray());
      baos.close();

//      billDetail.setOutput();
    }

    return billDetail;
  }

}
