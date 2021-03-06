package Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.linhkien.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import MenuItem.ItemMenu;
import MenuItem.Nhansu;

public class ChuotActivity extends AppCompatActivity {
    String DATABASE_NAME = "QLLK.db";
    SQLiteDatabase database;
    ListView lstDSNV;
    Button btnthem;
    ArrayList<Nhansu> list;
    NhansuAdapter nhansuAdapter;
    TextView textView;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ImageButton btnNs, btnLk,btnTim;
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    ListView listView;
    ArrayList<ItemMenu> arrayList;
    MenuAdapter adapter;
    EditText txtnoidung;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuot);
        lstDSNV = (ListView) findViewById(R.id.ListViewlk);
        AnhXa();
        addEvent();
        ActionBar();
        ActionViewFliper();
        ActionMenu();

        list = new ArrayList<>();
        nhansuAdapter = new NhansuAdapter(ChuotActivity.this, list);
        lstDSNV.setAdapter(nhansuAdapter);
        database = Database.initDatabase(ChuotActivity.this,DATABASE_NAME);

        Cursor cursor = database.rawQuery("select * from manhinh",null);
        for (int i = 0; i <cursor.getCount();i++){
            cursor.moveToPosition(i);
            int mabp = cursor.getInt(0);
            String tenbp = cursor.getString(1);
            String gia = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            list.add(new Nhansu(mabp,tenbp,gia,anh));
        }
        nhansuAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChuotActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = txtnoidung.getText().toString().trim();
                if(TextUtils.isEmpty(noidung)){
                    Toast.makeText(ChuotActivity.this,"Vui long nhap",Toast.LENGTH_SHORT).show();
                    return;
                }
                database = Database.initDatabase(ChuotActivity.this,DATABASE_NAME);
                Cursor cursor= database.rawQuery("select * from Chuot where TenChuot Like'%"+noidung+"%'",null);
                list.clear();
                for (int i=0; i<cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int mamh = cursor.getInt(0);
                    String tenmh = cursor.getString(1);
                    String gia = cursor.getString(2);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new Nhansu(mamh,tenmh,gia,anh));
                }
                nhansuAdapter.notifyDataSetChanged();
                nhansuAdapter = new NhansuAdapter(ChuotActivity.this,list);
                lstDSNV.setAdapter(nhansuAdapter);
            }
        });
    }

    private void ActionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Home",R.drawable.ic_home));
        arrayList.add(new ItemMenu("Nh??n s???",R.drawable.ns));
        arrayList.add(new ItemMenu("Linh ki???n",R.drawable.com));
        arrayList.add(new ItemMenu("C??i ?????t",R.drawable.ic_st));
        arrayList.add(new ItemMenu("Y??u th??ch",R.drawable.ic_he));
        arrayList.add(new ItemMenu("Ph???n h???i",R.drawable.ic_f));
        adapter = new MenuAdapter(this,R.layout.row_item,arrayList);
        listView.setAdapter(adapter);
    }

    private void ActionViewFliper() {
        ArrayList<String> manganh = new ArrayList<>();
        manganh.add("https://cdn.tgdd.vn/2021/11/banner/MHD-Desk-1920x380-4.jpg");
        manganh.add("https://cdn.tgdd.vn/Files/2016/05/12/827516/banner-thegioididong-so1online.jpg");
        manganh.add("https://cdn.tgdd.vn/Files/2017/07/07/1000678/banner_1200x628-800-resize.jpg");
        manganh.add("http://cdn.tgdd.vn/Files/2014/04/07/541207/phu-kien-50-940-300-1.jpg");
        manganh.add("https://cdn.tgdd.vn/Files/2020/07/10/1269536/800-450_800x450.png");
        for (int i = 0;i<manganh.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(manganh.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        txtnoidung = (EditText)findViewById(R.id.edittextNoidung);
        btnTim = (ImageButton) findViewById(R.id.btnSearch);
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        viewFlipper = findViewById(R.id.viewflipper);
        btnNs = (ImageButton)findViewById(R.id.imgBtNv);
        btnLk = (ImageButton)findViewById(R.id.imgBtLk);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerlayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        listView =(ListView)findViewById(R.id.list);
        textView=(TextView)findViewById(R.id.tvToolbar);
        textView.setText(getIntent().getStringExtra("key1"));
        btnthem = (Button)findViewById(R.id.buttonThem);
    }}

