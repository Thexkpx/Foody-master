package foody.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    List<menu> lstMenu ;
    private RecyclerViewAdapter mAdapter;
    ImageButton btn_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        lstMenu = new ArrayList<>();
        lstMenu.add(new menu("T1","Categorie","Description menu","25000",R.drawable.img1));
        lstMenu.add(new menu("T2","Categorie","Description menu","20000",R.drawable.img2));
        lstMenu.add(new menu("T3","Categorie","Description menu","25000",R.drawable.img3));
        lstMenu.add(new menu("Test menu 4","Categorie","Description menu","25000",R.drawable.img4));
        lstMenu.add(new menu("Test menu 5","Categorie","Description menu","30000",R.drawable.img5));
        lstMenu.add(new menu("Test menu 6","Categorie","Description menu","30000",R.drawable.img6));
        lstMenu.add(new menu("Test menu 7","Categorie","Description menu","30000",R.drawable.img7));
        lstMenu.add(new menu("Test menu 8","Categorie","Description menu","20000",R.drawable.img1));
        lstMenu.add(new menu("Test menu 9","Categorie","Description menu","20000",R.drawable.img2));
        lstMenu.add(new menu("Test menu 10","Categorie","Description menu","25000",R.drawable.img3));
        lstMenu.add(new menu("Test menu 11","Categorie","Description menu","25000",R.drawable.img4));
        lstMenu.add(new menu("Test menu 12","Categorie","Description menu","22000",R.drawable.img5));
        lstMenu.add(new menu("Test menu 13","Categorie","Description menu","20000",R.drawable.img6));
        lstMenu.add(new menu("Test menu 14","Categorie","Description menu","25000",R.drawable.img7));

        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstMenu);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        btn_cart = findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncartclick();
            }
        });

    }
    private void btncartclick(){
        Intent intentOrder = new Intent(MenuActivity.this,Order.class);
        startActivity(intentOrder);
    }

}
