/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.soop.util;

/**
 *
 * @author rcosco
 */
import com.mailjet.client.ClientOptions;
import static com.mailjet.client.ClientOptions.builder;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import rc.soop.action.ActionB;
import static rc.soop.action.ActionB.getPath;
import rc.soop.action.Constant;
import static rc.soop.action.Constant.rb;

/**
 *
 * @author rcosco
 */
public class SendMailJet {

    public static boolean sendMail(String name, String[] to, String[] cc, String txt, String subject) {
        return sendMail(name, to, cc, txt, subject, null);

    }

    public static boolean sendMail(String name, String[] to, String[] cc, String txt, String subject, File file) {
        try {
            MailjetClient client;
            MailjetRequest request;
            MailjetResponse response;

            String filename = "";
            String content_type = "";
            String b64 = "";

            ClientOptions options = builder()
                    .apiKey(rb.getString("mj.apikey"))
                    .apiSecretKey(rb.getString("mj.secret"))
                    .build();

            client = new MailjetClient(options);
//        client.setDebug(1);
            JSONArray dest = new JSONArray();
            JSONArray ccn = new JSONArray();
            JSONArray ccj = new JSONArray();

            if (to != null) {
                for (String s : to) {
                    dest.put(new JSONObject().put("Email", s)
                            .put("Name", ""));
                }
            } else {
                dest.put(new JSONObject().put("Email", "")
                        .put("Name", ""));
            }

            if (cc != null) {
                for (String s : cc) {
                    ccj.put(new JSONObject().put("Email", s)
                            .put("Name", ""));
                }
            } else {
                ccj.put(new JSONObject().put("Email", "")
                        .put("Name", ""));
            }

            try {
                ccn.put(new JSONObject().put("Email", getPath("mail.bcc"))
                        .put("Name", ""));
            } catch (Exception ee1) {
            }

            JSONObject mail = new JSONObject().put(Emailv31.Message.FROM, new JSONObject()
                    .put("Email", rb.getString("mj.user"))
                    .put("Name", name))
                    .put(Emailv31.Message.TO, dest)
                    .put(Emailv31.Message.CC, ccj)
                    .put(Emailv31.Message.BCC, ccn)
                    .put(Emailv31.Message.SUBJECT, subject)
                    .put(Emailv31.Message.HTMLPART, txt);

            if (file != null) {
                try {
                    filename = file.getName();
                    content_type = Files.probeContentType(file.toPath());
                    try ( InputStream i = new FileInputStream(file)) {
                        b64 = new String(Base64.encodeBase64(IOUtils.toByteArray(i)));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mail.put(Emailv31.Message.ATTACHMENTS, new JSONArray()
                        .put(new JSONObject()
                                .put("ContentType", content_type)
                                .put("Filename", filename)
                                .put("Base64Content", b64)));
            }

            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(mail));

            response = client.post(request);
            Constant.log.log(Level.INFO, "MJ: {0}", response.getStatus());
            return response.getStatus() == 200;
        } catch (Exception ex) {
            ActionB.trackingAction("service", Utility.estraiEccezione(ex));
            return false;
        }

    }

}
