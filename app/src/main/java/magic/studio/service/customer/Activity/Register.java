package magic.studio.service.customer.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import magic.studio.service.customer.R;
import magic.studio.service.customer.WebService.WebServiceURL;

public class Register extends AppCompatActivity implements View.OnClickListener {

    CircleImageView pick_user_image;
    EditText username_EditText, email_EditText, mobile_EditText, password_EditText;
    Button register_btn;
    String selectedImagePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        pick_user_image = (CircleImageView) findViewById(R.id.pick_user_image);
        username_EditText = (EditText) findViewById(R.id.username_EditText);
        email_EditText = (EditText) findViewById(R.id.email_EditText);
        mobile_EditText = (EditText) findViewById(R.id.mobile_EditText);
        password_EditText = (EditText) findViewById(R.id.password_EditText);
        register_btn = (Button) findViewById(R.id.register_btn);

        pick_user_image.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    private void RegisterAPI() {
        HashMap<String, String> param = new HashMap<>();
        param.put("name", username_EditText.getText().toString());
        param.put("email", email_EditText.getText().toString());
        param.put("password", password_EditText.getText().toString());
        param.put("mobile", mobile_EditText.getText().toString());
        param.put("type", "customer");

        AndroidNetworking.upload(WebServiceURL.REGISTER)
                .addMultipartParameter(param)
                .addMultipartFile("image", new File(selectedImagePath))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")){

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Response","Register : " + response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Response","Error : " + anError.getErrorCode());
                        Log.e("Response","Error : " + anError.getErrorBody());
                        Log.e("Response","Error : " + anError.getErrorDetail());
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            try {
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    selectedImagePath = picturePath;
                    File filenew = new File(picturePath);
                    Picasso.with(Register.this).load(filenew).into(pick_user_image);
                    Log.e("in","path : " + selectedImagePath);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(Register.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pick_user_image:
                Intent i = new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
                break;
            case R.id.register_btn:
                RegisterAPI();
                break;
        }
    }
}
