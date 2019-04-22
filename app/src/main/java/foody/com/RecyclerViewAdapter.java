package foody.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext ;
    Button bt;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String FirstNameHolder, LastNameHolder, EmailHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://hoctiengviet.net/food_order/Get_Order.php";
    private List<menu> mData ;
    private AdapterView.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public RecyclerViewAdapter(Context mContext, List<menu> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }
    public RecyclerViewAdapter(ArrayList<menu> exampleList) {
        mData = exampleList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_food,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.tv_menu_name.setText(mData.get(position).getMenu_name());
        holder.img_menu_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.gia_menu.setText(mData.get(position).getGia());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_name;
        ImageView img_menu_thumbnail;
        TextView gia_menu;
        Button btncong;
        Button btntru;
        TextView slmenu;
        Button addcart;
        EditText dc;
        TextView tvdc;

        RequestQueue queue = Volley.newRequestQueue(MySuperAppApplication.getContext());
        public MyViewHolder(final View itemView) {

            super(itemView);

            tv_menu_name = (TextView) itemView.findViewById(R.id.menu_name_id);
            img_menu_thumbnail = (ImageView) itemView.findViewById(R.id.menu_image_id);
            gia_menu = (TextView) itemView.findViewById(R.id.menu_gia_id);

            btncong = (Button) itemView.findViewById(R.id.btn_congmenu);
            btntru = (Button) itemView.findViewById(R.id.btn_trumenu);
            slmenu = (TextView) itemView.findViewById(R.id.txt_slmenu);
            addcart = (Button) itemView.findViewById(R.id.btn_addcart);

         //   dc = (EditText) itemView.findViewById(R.id.editdiachi);

            btncong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int intsoluong = Integer.parseInt(slmenu.getText().toString()) + 1;
                    String strsoluong = String.valueOf(intsoluong);
                    slmenu.setText(strsoluong);
                }
            });
            btntru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soluongmenu = Integer.parseInt(slmenu.getText().toString());
                    if (soluongmenu < 2) {
                        slmenu.setText("1");
                    }else {
                        int intsoluong = soluongmenu - 1;
                        String strsoluong = String.valueOf(intsoluong);
                        slmenu.setText(strsoluong);
                    }
                }
            });

            addcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addcart.getText() == "✓"){
                        addcart.setText("ADD");
                    }else{

                        String HttpUrl = "http://hoctiengviet.net/food_order/iscart.php";
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
                                params.put("ten_menu", tv_menu_name.getText().toString());
                                params.put("giamenu", gia_menu.getText().toString());
                                params.put("soluong", slmenu.getText().toString());
                                return params;
                            }
                        };
                        queue.add(postRequest);

                               // addcart.setText(tv_menu_name.getText());

                    }
                }
            });

        }
    }

}
