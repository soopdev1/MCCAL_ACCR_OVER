/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.soop.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import static rc.soop.action.ActionB.trackingAction;
import rc.soop.action.Constant;
import static rc.soop.action.Constant.rb;

/**
 *
 * @author rcosco
 */
public class GoogleRecaptcha {

    public static final String SECRET_KEY = rb.getString("google.secret");
    public static final String SITE_KEY = rb.getString("google.sitekey");

    public static boolean isValid(String clientRecaptchaResponse) {

        try {
            final String RECAPTCHA_SERVICE_URL = "https://www.google.com/recaptcha/api/siteverify";
//        final String SECRET_KEY = "6Lfr-eMUAAAAANZ9VeTQ0FmZ_EjEgQdNPYRX08_p";

            if (clientRecaptchaResponse == null || "".equals(clientRecaptchaResponse)) {
                return false;
            }
            URL obj = new URL(RECAPTCHA_SERVICE_URL);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams
                    = "secret=" + SECRET_KEY
                    + "&response=" + clientRecaptchaResponse;

            con.setDoOutput(true);
            try ( DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(postParams);
                wr.flush();
            }
            StringBuilder response = new StringBuilder();
            try ( BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            Constant.log.log(Level.INFO, "recaptcha verify server code: {0}", con.getResponseCode());
            Constant.log.log(Level.INFO, "recaptcha verify server response: {0}", response.toString());
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());
            Boolean success = (Boolean) json.get("success");

            if (success) {
                Double score = (Double) json.get("score");
                return score >= 0.5;
            } else {
                if (response.toString().contains("timeout-or-duplicate")) {
                    return true;
                }
            }
        } catch (Exception ex) {
            trackingAction("service", Utility.estraiEccezione(ex));
        }
        return false;
    }

}
