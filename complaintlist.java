package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class complaintlist extends AppCompatActivity {
String status,dbid_str;
    String staffid;
    TextView messages;
    ListView listView;
    String stfid;
    ArrayList<String> dbid=new ArrayList<String>();
    ArrayList<String> area=new ArrayList<String>();
    ArrayList<String> place=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagelist);
        listView=findViewById(R.id.listview);
        staffid=getIntent().getStringExtra("staffid");
        new complaint_list(complaintlist.this).execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbid_str=dbid.get(position);
                new complaint_list1(complaintlist.this).execute();
            }
        });
    }



    class complaint_list extends AsyncTask<String, String, String> {

        Context ccc;
        String url = "";

        complaint_list(Context c) {

            ccc = c;
        }

        String g = "error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("staffid", staffid));


                url = "http://192.168.43.89/waste/stafflist_complaint.php";


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
            Log.e("SMK",result);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            try {
                JSONArray jsonarray = new JSONArray(result);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    dbid.add(jsonobject.getString("dustbinid"));
                    area.add(jsonobject.getString("area"));
                    place.add(jsonobject.getString("place"));

//                        Toast.makeText(getApplicationContext(), items.get(i), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) { e.printStackTrace(); }

            CustomAdapter customAdapter=new CustomAdapter();
            listView.setAdapter(customAdapter);
        }
    }




    class complaint_list1 extends AsyncTask<String, String, String> {

        Context ccc;
        String url = "";

        complaint_list1(Context c) {

            ccc = c;
        }

        String g = "error";

        @Override
        protected String doInBackground(String... arg0) {
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("status", status));
                nameValuePairs.add(new BasicNameValuePair("dbid", dbid_str));


                url = "http://192.168.43.89/waste/stafflist_complaint1.php";


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
            Log.e("SMK",result);

            Toast.makeText(getApplicationContext(), "Resolved", Toast.LENGTH_SHORT).show();

        }
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dbid.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.comp_list_inflator,null);


            //ImageView loc=(ImageView)convertView.findViewById(R.id.loc);
            TextView dbidd=(TextView)convertView.findViewById(R.id.dudid);
            TextView areaa=(TextView)convertView.findViewById(R.id.area);
            TextView placee=(TextView)convertView.findViewById(R.id.place);

            dbidd.setText(dbid.get(position));
            areaa.setText(area.get(position));
            placee.setText(place.get(position));

            //thumbnail.setImageResource(R.drawable.pp);
            // loc.setBackgroundResource(0);
            // loc.setImageResource(R.drawable.location);

            return convertView;
        }


    }
}


