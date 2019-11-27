package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class staff extends AppCompatActivity {
    Button complaint,message,map;
    String s_comp,s_msg;
String staffid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        staffid=getIntent().getStringExtra("staffid");

        map=findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

        complaint = findViewById(R.id.complaint);
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new staffcls(staff.this).execute();
                Intent i = new Intent(staff.this, complaintlist.class);
                i.putExtra("staffid",staffid);
                startActivity(i);


            }
        });

        message=findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new staffcls(staff.this).execute();
                Intent i = new Intent(staff.this, messagelist.class);
                i.putExtra("staffid",staffid);
                startActivity(i);
            }
        });

    }




    class staffcls extends AsyncTask<String, String, String> {

        Context ccc;
        String url = "";

        staffcls(Context c) {

            ccc = c;
        }

        String g = "error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("complaints", s_comp));
                nameValuePairs.add(new BasicNameValuePair("messages", s_msg));



                url = "http://192.168.43.89/waste/staff.php";

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        }
    }
}
