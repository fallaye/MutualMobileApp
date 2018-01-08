package mobileappscompany.mutualmobileapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity: ";
    private RecyclerView recyclerView;
    private AdapterReceipe adapterReceipe;
    private List<Receipe> receipeList;
    public static final String URL_DATA = "https://api.edamam.com/search?q=chicken&app_id=3dd1ad8b&app_key=c35916a6e9366e058de33e706092343a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        receipeList = new ArrayList<>();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        //From Volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("hits");

                            //JSONArray array = new JSONArray(response);

                            String uri, label, image;
                            Receipe receipe;

                            for (int i = 0; i < array.length(); i++) {


                                JSONObject object = array.getJSONObject(i);

                                Log.d(TAG, "JSON Object: " + array.getJSONObject(i));

                                for (int j = 0; j <object.length() ; j++) {

                                    //Log.d(TAG, "Uri: " + object.getString("uri"));
                                    //Log.d(TAG, "Label: " + object.getString("label"));

                                    receipe = new Receipe(
                                            object.getString("uri"),
                                            object.getString("label"),
                                            object.getString("image")

                                    );

                                    receipeList.add(receipe);
                                }
                            }
                            adapterReceipe = new AdapterReceipe(getApplicationContext(), receipeList);
                            recyclerView.setAdapter(adapterReceipe);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}