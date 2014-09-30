package me.passos.talks.judconbr2014.androidclient;

import android.app.Application;
import android.widget.Toast;
import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.unifiedpush.PushConfig;
import org.jboss.aerogear.android.unifiedpush.PushRegistrar;
import org.jboss.aerogear.android.unifiedpush.Registrations;

import java.net.URI;
import java.net.URISyntaxException;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerDeviceOnPushServer();
    }

    public void registerDeviceOnPushServer() {

        String UNIFIED_PUSH_URL = "";
        String GCM_SENDER_ID = "";
        String VARIANT_ID = "";
        String SECRET = "";

        try {
            PushConfig config = new PushConfig(new URI(UNIFIED_PUSH_URL), GCM_SENDER_ID);
            config.setVariantID(VARIANT_ID);
            config.setSecret(SECRET);

            Registrations registrations = new Registrations();
            PushRegistrar registrar = registrations.push("registrar", config);
            registrar.register(getApplicationContext(), new Callback<Void>() {
                @Override
                public void onSuccess(Void data) {
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
