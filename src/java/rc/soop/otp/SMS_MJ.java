package rc.soop.otp;

import com.mailjet.client.ClientOptions;
import static com.mailjet.client.ClientOptions.builder;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.sms.SmsSend;
import java.util.logging.Level;
import org.apache.commons.lang3.exception.ExceptionUtils;
import static rc.soop.action.ActionB.trackingAction;
import rc.soop.action.Constant;
import static rc.soop.action.Constant.rb;

/**
 *
 * @author Administrator
 */
public class SMS_MJ {

    public static boolean sendSMS2022(String cell, String msg) {
        try {
            ClientOptions options = builder().bearerAccessToken(rb.getString("mj.sms.token")).build();
            MailjetClient client = new MailjetClient(options);
            MailjetRequest request = new MailjetRequest(SmsSend.resource)
                    .property(SmsSend.FROM, rb.getString("mj.sms.name"))
                    .property(SmsSend.TO, "+39" + cell)
                    .property(SmsSend.TEXT, msg);
            MailjetResponse response = client.post(request);
            if (response.getStatus() == 200) {
                return true;
            }
            Constant.log.log(Level.INFO, "sendSMS2022: {0}", response.getStatus());
            Constant.log.log(Level.INFO, "sendSMS2022: {0}", response.toString());
        } catch (Exception e) {
            trackingAction("service", "SMS ERROR: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

//    public static void main(String[] args) {
//        sendSMS2022("3286137172", "testing message");
//    }
}
