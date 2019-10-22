package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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

public class  signup extends AppCompatActivity {
    EditText uname;
    EditText phone;
    EditText password;
    EditText area;
    EditText place;
    EditText name;
    EditText email;
    Spinner spinner;
    Button submit;
    String[] item = {"user", "staff"};
String s_username,s_name,s_password,s_phone,s_area,s_place,s_email,s_type;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uname = findViewById(R.id.uname);
        phone = findViewById(R.id.phone);
        password= findViewById(R.id.password);
        area = findViewById(R.id.area);
        place = findViewById(R.id.place);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

          spin = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_username=uname.getText().toString();
                s_name=name.getText().toString();
                s_password=password.getText().toString();
                s_phone=phone.getText().toString();
                s_area=area.getText().toString();
                s_place=place.getText().toString();
                s_email=email.getText().toString();
                s_type=spin.getSelectedItem().toString();


                new sign_up(signup.this).execute();
                Intent i=new Intent(signup.this,login.class);
                startActivity(i);
            }
        });
    }


public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
      Toast.makeText(getApplicationContext(), item[position], Toast.LENGTH_LONG).show();
  }

        public void onNothingSelected(AdapterView<?> arg0) {

        }



    class sign_up extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        sign_up(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name",s_name));
                nameValuePairs.add(new BasicNameValuePair("username",s_username));
                nameValuePairs.add(new BasicNameValuePair("password",s_password));
                nameValuePairs.add(new BasicNameValuePair("phone",s_phone));
                nameValuePairs.add(new BasicNameValuePair("area",s_area));
                nameValuePairs.add(new BasicNameValuePair("place",s_place));
                nameValuePairs.add(new BasicNameValuePair("email",s_email));
                nameValuePairs.add(new BasicNameValuePair("type",s_type));



                url="http://192.168.43.89/waste/signup.php";


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
Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

        }
    }


}

