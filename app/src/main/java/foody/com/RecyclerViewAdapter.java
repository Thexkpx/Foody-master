package foody.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.Context.WIFI_SERVICE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<menu> list;
    RequestOptions options ;
    Random random = new Random();
    String id = UUID.randomUUID().toString();

    public RecyclerViewAdapter(Context context, List<menu> list) {
        this.context = context;
        this.list = list;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img1)
                .error(R.drawable.img1);


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_item_food, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        menu data = list.get(position);
        holder.menu_id.setText(String.valueOf(data.getMenu_id()));
        holder.tv_menu_name.setText(data.getMenu_name());
        holder.gia_menu.setText(String.valueOf(data.getPrice()));
        Glide.with(context).load(data.getImage_url()).apply(options).into(holder.img);

    }

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

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }
    public RecyclerViewAdapter(ArrayList<menu> exampleList) {
        mData = exampleList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_name;
        ImageView img;
        TextView menu_id;
        TextView gia_menu;
        Button btncong;
        Button btntru;
        TextView slmenu;
        Button addcart;
        EditText dc;
        TextView tvdc;
        TextView textViewusernae;
        TextView txtusernamecard;
        Random random = new Random();

        final int mi = random.nextInt(9999 - 1000) + 1000;
        final String t= Integer.toString(mi);
       // String id = UUID.randomUUID().toString();



        RequestQueue queue = Volley.newRequestQueue(MySuperAppApplication.getContext());
        public MyViewHolder(final View itemView) {

            super(itemView);

            tv_menu_name = (TextView) itemView.findViewById(R.id.menu_name_id);
            img = itemView.findViewById(R.id.menu_image_id);
            gia_menu = (TextView) itemView.findViewById(R.id.menu_gia_id);
            menu_id = (TextView) itemView.findViewById(R.id.menu_id);
            textViewusernae = (TextView) itemView.findViewById(R.id.txtname);
            btncong = (Button) itemView.findViewById(R.id.btn_congmenu);
            btntru = (Button) itemView.findViewById(R.id.btn_trumenu);
            slmenu = (TextView) itemView.findViewById(R.id.txt_slmenu);
            addcart = (Button) itemView.findViewById(R.id.btn_addcart);
            txtusernamecard=(TextView) itemView.findViewById(R.id.textusername);
         //   txtusernamecard.setText(textViewusernae.getText().toString());
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
                                WifiManager wifiMan = (WifiManager) MySuperAppApplication.getContext().getSystemService(Context.WIFI_SERVICE);
                                WifiInfo wifiInf = wifiMan.getConnectionInfo();
                                int ipAddress = wifiInf.getIpAddress();
                                String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));

                               params.put("username",ip);
                                params.put("menu_id", menu_id.getText().toString());
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
