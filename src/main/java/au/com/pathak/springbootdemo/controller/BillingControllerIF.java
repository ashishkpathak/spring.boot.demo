package au.com.pathak.springbootdemo.controller;

import au.com.pathak.springbootdemo.model.BillDetail;
import au.com.pathak.springbootdemo.model.BillSummary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BillingControllerIF {

  @RequestMapping("/billingaccount/{account}")
  public BillSummary getCustomersViaFirstName(final @PathVariable String account) ;


///billingaccount/{billingAccount}/invoice/{invoiceid}?format=pdf
  @RequestMapping("/billingaccount/{account}/invoice/{invoiceid}")
  public BillDetail getBillDetails(final @PathVariable String account, final @PathVariable String invoiceid, @RequestParam(name = "outputFormat", required = false) String outputFormat) throws Exception;

}
