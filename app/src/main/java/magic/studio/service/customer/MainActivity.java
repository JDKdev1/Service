package magic.studio.service.customer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import magic.studio.service.customer.Activity.Dashboard;
import magic.studio.service.customer.Activity.HomeScreen;
import magic.studio.service.customer.Activity.Login;
import magic.studio.service.customer.Utils.GlobalValue;

public class MainActivity extends AppCompatActivity {

    GlobalValue globalValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalValue = new GlobalValue(MainActivity.this);
        if (globalValue.getString("login")==null){
            globalValue.put("login","");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (globalValue.getString("login").equals("true")){
                    intent = new Intent(getBaseContext(), Dashboard.class);
                } else if (globalValue.getString("login").equals("false") || globalValue.getString("login").equals("")){
                    intent = new Intent(getBaseContext(), Login.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
