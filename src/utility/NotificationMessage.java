package utility;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NotificationMessage {

    public static ArrayList<String> notificationOTPMessage() throws IOException {

        String time=String.valueOf(System.currentTimeMillis()).substring(9,13);

        URL url=new URL("https://cliq.zoho.com/api/v2/buddies/karthikeyan.l@zohocorp.com/message?authtoken=603898c52c725913c6434edbe1e6d286");
        HttpURLConnection con=(HttpURLConnection)url.openConnection();
        String message="{\"text\":\"your OTP Number is "+time+"\"}";
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        wr.write(message.getBytes());
        String code=String.valueOf(con.getResponseCode());
        wr.flush();
        con.disconnect();

        ArrayList<String> array=new ArrayList<String>();
        array.add(time);
        array.add(code);

        return array;
    }

    public static void notificationMessage() throws IOException {

        String text="";

        URL url=new URL("https://cliq.zoho.com/api/v2/buddies/karthikeyan.l@zohocorp.com/message?authtoken=603898c52c725913c6434edbe1e6d286");
        HttpURLConnection con=(HttpURLConnection)url.openConnection();
        String message="{\"text\":\""+text+"\"}";
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream( con.getOutputStream());

        wr.write(message.getBytes());

        System.out.println(con.getResponseCode());
        wr.flush();
    }

}