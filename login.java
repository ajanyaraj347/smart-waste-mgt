package com.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    Button login;
TextView signup;
EditText username;
EditText password;
Spinner spinner;
String uname,pwd,type;
int res;
    String[] item = {"user", "staff"};
    Spinner spin;
    String token;
    String staffid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        spin = (Spinner) findViewById(R.id.spinner);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("SSSSMMMKKK", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("SSSSMMMKKK", token);
                        Toast.makeText(login.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            uname=username.getText().toString();
            pwd=password.getText().toString();
            type=item[spin.getSelectedItemPosition()];
                new signin(login.this).execute();


            }
        });
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,signup.class);
                startActivity(i);
            }
        });


    }





    class signin extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        signin(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("username",uname));
                nameValuePairs.add(new BasicNameValuePair("password",pwd));
                nameValuePairs.add(new BasicNameValuePair("type",type));




                url="http://192.168.43.89/waste/login.php";


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                g = httpClient.execute(httpPost, responseHandler);

                // HttpEntity entity = response.getEntity();


            } catch (NullPointerException e) {
                //Toast.makeText(ccc, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
//		Toast.makeText(ccc,e.toString(), Toast.LENGTH_LONG).show();
                return e.toString();
            }
            return g;

        }

        @Override


        protected void onPostExecute(String result) {
            //  hlist.removeAllViewsInLayout();
            res=Integer.parseInt(result.trim());
            Toast.makeText(ccc,result.trim(), Toast.LENGTH_LONG).show();
            staffid=result;
            if(Integer.parseInt(result.trim())>0)
            {
                if (type.equals("user")) {
                    Intent i = new Intent(login.this, user.class);
                    startActivity(i);

                }
                else {
                    new tocken(login.this).execute();

                }

            }
            else
            {
                Toast.makeText(ccc,"incorrect username or password", Toast.LENGTH_LONG).show();
            }

        }
    }



    class tocken extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        tocken(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id",res+""));
                nameValuePairs.add(new BasicNameValuePair("type",type));
                nameValuePairs.add(new BasicNameValuePair("token",token));




                url="http://192.168.43.89/waste/update_register.php";


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                g = httpClient.execute(httpPost, responseHandler);

                // HttpEntity entity = response.getEntity();


            } catch (NullPointerException e) {
                //Toast.makeText(ccc, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
//		Toast.makeText(ccc,e.toString(), Toast.LENGTH_LONG).show();
                return e.toString();
            }
            return g;

        }

        @Override


        protected void onPostExecute(String result) {
            //  hlist.removeAllViewsInLayout();
            Intent i = new Intent(login.this, staff.class);
            i.putExtra("staffid",staffid);
            startActivity(i);

        }
    }



}
