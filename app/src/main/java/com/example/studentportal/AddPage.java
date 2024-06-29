package com.example.studentportal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddPage extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    AppCompatButton b1;
    String apiurl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_page);
        ed1=(EditText) findViewById(R.id.firstnam);
        ed2=(EditText) findViewById(R.id.lastnam);
        ed3=(EditText) findViewById(R.id.colleg);
        ed4=(EditText) findViewById(R.id.dob);
        ed5=(EditText) findViewById(R.id.course);
        ed6=(EditText) findViewById(R.id.mob);
        ed7=(EditText) findViewById(R.id.email);
        ed8=(EditText) findViewById(R.id.address);
        b1=(AppCompatButton) findViewById(R.id.adde);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getfName=ed1.getText().toString();
                String getlName=ed2.getText().toString();
                String getCollege=ed3.getText().toString();
                String getdob=ed4.getText().toString();
                String getcourse=ed5.getText().toString();
                String getmob=ed6.getText().toString();
                String getemail=ed7.getText().toString();
                String getaddress=ed8.getText().toString();
               //creating json object
                JSONObject stu=new JSONObject();
                try {
                    stu.put("firstname",getfName);
                    stu.put("lastname",getlName);
                    stu.put("college",getCollege);
                    stu.put("dob",getdob);
                    stu.put("course",getcourse);
                    stu.put("mobile",getmob);
                    stu.put("email",getemail);
                    stu.put("address",getaddress);
                }
                catch(JSONException e)
                {
                    throw new RuntimeException(e);
                }
                //JSON object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        stu,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}