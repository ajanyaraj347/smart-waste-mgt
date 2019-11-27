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

public class  complaints extends AppCompatActivity {
    EditText ecomplaint;
    EditText area;
    EditText place;
    Button submit;

    String c_desc,c_area,c_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        ecomplaint = findViewById(R.id.ecomplaint);
        area = findViewById(R.id.area);
        place = findViewById(R.id.place);



        //ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, item);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(aa);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_desc=ecomplaint.getText().toString();
                c_area=area.getText().toString();
                c_place=place.getText().toString();



                new complaintcls(complaints.this).execute();
                Intent i=new Intent(complaints.this,user.class);
                startActivity(i);
            }
        });
    }

    class complaintcls extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        complaintcls(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("description",c_desc));
                nameValuePairs.add(new BasicNameValuePair("area",c_area));
                nameValuePairs.add(new BasicNameValuePair("place",c_place));


                url="http://192.168.43.89/waste/complaints.php";


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

