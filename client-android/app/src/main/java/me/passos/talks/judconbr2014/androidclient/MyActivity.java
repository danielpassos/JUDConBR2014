package me.passos.talks.judconbr2014.androidclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import org.jboss.aerogear.android.unifiedpush.MessageHandler;
import org.jboss.aerogear.android.unifiedpush.Registrations;

public class MyActivity extends Activity implements MessageHandler {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Registrations.registerMainThreadHandler(this);
        Registrations.unregisterBackgroundThreadHandler(NotificationBarMessageHandler.instance);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Registrations.unregisterMainThreadHandler(this);
        Registrations.registerBackgroundThreadHandler(NotificationBarMessageHandler.instance);
    }

    @Override
    public void onDeleteMessage(Context context, Bundle bundle) {

    }

    @Override
    public void onMessage(Context context, Bundle bundle) {
        Toast.makeText(getApplicationContext(),
                bundle.getString("alert"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {

    }
}
