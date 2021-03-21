package Esprit.PiDev.RestController;

import Esprit.PiDev.Entity.PaypalPaymentIntent;
import Esprit.PiDev.Entity.PaypalPaymentMethod;
import Esprit.PiDev.Service.PaypalService;
import Esprit.PiDev.URLUtils.URLUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Payment_Rest_Controller {
    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";

    private final org.jboss.logging.Logger logger = LoggerFactory.logger(Payment_Rest_Controller.class);

    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(  value = "/pay")
    public String pay(HttpServletRequest request){
        String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
        try {
            Payment payment = paypalService.createPayment(
                    4.00,
                    "USD",
                    PaypalPaymentMethod.PAYPAL,
                    PaypalPaymentIntent.SALE,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }

    @RequestMapping("/PAYPAL_SUCCESS_URL")
    public String successPay(  String paymentId,  String payerId){
        try {
            Payment payment = paypalService.executePayment("123", "123ons");
            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")){
                return "success";
            }
        } catch (PayPalRESTException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/";
    }
}
