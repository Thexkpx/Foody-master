package foody.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Order extends AppCompatActivity {
    List<order_class> lstMenu ;
    private String url = "http://hoctiengviet.net/food_order/json/get_cart.php";

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<movie> movieList;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        FirebaseApp.initializeApp(MySuperAppApplication.getContext());
        final Random random = new Random();
        mList = findViewById(R.id.listOrder);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(getApplicationContext(),movieList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();

        final RequestQueue queue = Volley.newRequestQueue(MySuperAppApplication.getContext());

        ImageButton btnbye;
        final EditText phong;
        btnbye = findViewById(R.id.btn_dat);
        phong = findViewById(R.id.editphong);

        btnbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phong.getText() != null ){
                   //   FirebaseDatabase database = FirebaseDatabase.getInstance();
                   // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child("b7da879XqcgpVslLMZrXx794MYl2").child("diachi");
                    int mi = random.nextInt(9999 - 1000) + 1000;
                    //ref.setValue(mi);

                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child("b7da879XqcgpVslLMZrXx794MYl2").child("diachi").setValue(mi);


                    String HttpUrl = "http://hoctiengviet.net/food_order/buynow.php";
                    StringRequest postRequest = new StringRequest(Request.Method.POST, HttpUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("respon", response);
                                    Toast.makeText(MySuperAppApplication.getContext(), response, Toast.LENGTH_LONG).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MySuperAppApplication.getContext(), error.toString(), Toast.LENGTH_LONG).show();

                                }
                            }
                    )
                    {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String> params = new HashMap<String, String>();
                            // params.put("name", "Alif");
                            // params.put("domain", "http://itsalif.info");
                            params.put("diachi", phong.getText().toString());
                            return params;
                        }
                    };
                    queue.add(postRequest);
                    btnbuyclick();

                    Toast.makeText(Order.this,"Complete",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Order.this,"Please!! Nhập Phòng!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void btnbuyclick() {
        Intent intentOrder = new Intent(Order.this, MenuActivity.class);
        startActivity(intentOrder);
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        movie movie = new movie();
                        movie.setTen_menu(jsonObject.getString("menu_id"));
                        movie.setSoluong(jsonObject.getInt("soluong"));

                        movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MySuperAppApplication.getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
