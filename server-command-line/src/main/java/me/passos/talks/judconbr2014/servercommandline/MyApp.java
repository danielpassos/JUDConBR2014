package me.passos.talks.judconbr2014.servercommandline;

import org.jboss.aerogear.unifiedpush.JavaSender;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;

public class MyApp {

    public static void main(String[] args) {

        System.setProperty("jsse.enableSNIExtension", "false");

        String UNIFIED_PUSH_URL = "";
        String APPLICATION_ID = "";
        String MASTER_SECRET = "";


        UnifiedMessage message = new UnifiedMessage.Builder()
                .pushApplicationId(APPLICATION_ID)
                .masterSecret(MASTER_SECRET)
                .alert("Message sent from server")
                .build();

        JavaSender sender = new SenderClient.Builder(UNIFIED_PUSH_URL).build();

        sender.send(message, new MessageResponseCallback() {
            @Override
            public void onComplete(int i) {
                System.out.println("Yay. The message was sent");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("An error occurred");
            }
        });

    }

}