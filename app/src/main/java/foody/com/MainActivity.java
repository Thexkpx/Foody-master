package foody.com;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtadd;
    private Button btnadd;
    private Button bt1;
    private Button bt2;
    private Context context;
    private MySQLConnect mySQLConnect;
    private ListView ListQuan;
    private List<String> items;
    private ArrayAdapter<String> adt;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        init();
        update();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtadd.getText().toString());
                items.add(txtadd.getText().toString());
                adt.notifyDataSetChanged();
                txtadd.setText("");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("1");
                myRef.setValue("1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("1");
                myRef.setValue("2");
            }
        });
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle("test").setSmallIcon(R.drawable.ic_launcher_foreground).build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0,notify);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void update(){
        items = mySQLConnect.getData();
        //ListQuan.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListQuan.setAdapter(adt);
    }

    public void init(){
        txtadd = findViewById(R.id.txtadd);
        btnadd = findViewById(R.id.btnadd);
        bt1 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button3);
        ListQuan = findViewById(R.id.ListQuan);
        mySQLConnect = new MySQLConnect(MainActivity.this);
    }
}
