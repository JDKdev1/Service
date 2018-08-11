package magic.studio.service.customer.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import dmax.dialog.SpotsDialog;
import magic.studio.service.customer.R;
import magic.studio.service.customer.Utils.GlobalValue;
import magic.studio.service.customer.WebService.WebServiceURL;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView create_account;
    EditText email_EditText, password_EditText;
    Button login_btn;
    SpotsDialog dialog;
    String emailPattern = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
    GlobalValue globalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        globalValue = new GlobalValue(Login.this);
        init();
    }

    private void init() {
        create_account = (TextView) findViewById(R.id.create_account);
        email_EditText = (EditText) findViewById(R.id.email_EditText);
        password_EditText = (EditText) findViewById(R.id.password_EditText);
        login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(this);
        create_account.setOnClickListener(this);
    }

    private void LoginAPI() {
        dialog = new SpotsDialog(Login.this, R.style.Custom);
        dialog.show();
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email_EditText.getText().toString());
        map.put("password", password_EditText.getText().toString());

        AndroidNetworking.post(WebServiceURL.LOGIN)
                .setPriority(Priority.HIGH)
                .addBodyParameter(map)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        Log.e("in","Response : " + response);
                        try {
                            if (response.getString("success").equalsIgnoreCase("true")){
                                Toast.makeText(Login.this, response.getString("message"), Toast.LENGTH_SHORT).show();

                                JSONObject data = response.getJSONObject("data");
                                globalValue.put("id", data.getString("id"));
                                globalValue.put("name", data.getString("name"));
                                globalValue.put("email", data.getString("email"));
                                globalValue.put("mobile", data.getString("mobile"));
                                globalValue.put("api_token", data.getString("api_token"));
                                globalValue.put("image_url", data.getString("image"));
                                globalValue.put("type", data.getString("type"));
                                globalValue.put("login", "true");
                                Intent intent = new Intent(Login.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else if (response.getString("status").equalsIgnoreCase("error")){
                                JSONObject msg = response.getJSONObject("message");
                                if (msg.getJSONArray("email").length() > 0){
                                    Toast.makeText(Login.this, msg.getJSONArray("email").toString(), Toast.LENGTH_SHORT).show();
                                }
                                if (msg.getJSONArray("password").length() > 0){
                                    Toast.makeText(Login.this, msg.getJSONArray("password").toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                globalValue.put("login", "false");
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                if (!email_EditText.getText().toString().isEmpty() && !password_EditText.getText().toString().isEmpty()
                    && email_EditText.getText().toString().trim().matches(emailPattern)){
                    LoginAPI();
                }
                if (email_EditText.getText().toString().isEmpty()){
                    Toast.makeText(this, "Enter your Emailid", Toast.LENGTH_SHORT).show();
                } else if (password_EditText.getText().toString().isEmpty()){
                    Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
                }else if (!email_EditText.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(this, "Email is Invalid", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.create_account:
                Intent intent = new Intent(Login.this, Register.class);
                startActivityForResult(intent, 142);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
