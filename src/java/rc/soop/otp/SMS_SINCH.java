/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc.soop.otp;

import com.sinch.xms.ApiConnection;
import com.sinch.xms.SinchSMSApi;
import com.sinch.xms.api.MtBatchTextSmsResult;
import java.util.logging.Level;
import org.apache.commons.lang3.exception.ExceptionUtils;
import static rc.soop.action.ActionB.trackingAction;
import static rc.soop.action.Constant.log;
import static rc.soop.action.Constant.rb;

/**
 *
 * @author Administrator
 */
public class SMS_SINCH {

    public static boolean sendSMS2024(String cell, String msg) {
        try (ApiConnection conn = ApiConnection.builder()
                .servicePlanId(rb.getString("si.sms.spid"))
                .token(rb.getString("si.sms.token"))
                .start()) {
            String[] recipients = {"39"+cell};
            MtBatchTextSmsResult batch
                    = conn.createBatch(
                            SinchSMSApi.batchTextSms()
                                    .sender(rb.getString("si.sms.sender"))
                                    .addRecipient(recipients)
                                    .body(msg)
                                    .build());
            log.log(Level.INFO, "SMS OK -> {0} -- ID:{1}", new Object[]{cell, batch.id()});
            return true;
        } catch (Exception ex) {
            trackingAction("service", "SMS ERROR: " + ExceptionUtils.getStackTrace(ex));
        }
        return false;
    }
}
