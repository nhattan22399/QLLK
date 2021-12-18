package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.linhkien.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import MenuItem.ItemMenu;

public class Main4Activity extends AppCompatActivity {
    TextView textView;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ImageButton btnMan, btnPhim, btnChuot, btnLkkhac;
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    ListView listView;
    ArrayList<ItemMenu> arrayList;
    MenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        AnhXa();
        ActionBar();
        ActionViewFliper();
        ActionMenu();
        ActionBtn();


    }

    private void ActionBtn() {
        btnLkkhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,LKkhacActivity.class);
                startActivity(intent);
            }
        });
        btnChuot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,ChuotActivity.class);
                startActivity(intent);
            }
        });
        btnMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,ManhinhActivity.class);
                startActivity(intent);
            }
        });

        btnPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main4Activity.this,BanphimActivity.class);

                startActivity(intent);
            }
        });
    }

    private void ActionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Home",R.drawable.ic_home));
        arrayList.add(new ItemMenu("Nhân sự",R.drawable.ns));
        arrayList.add(new ItemMenu("Linh kiện",R.drawable.com));
        arrayList.add(new ItemMenu("Cài đặt",R.drawable.ic_st));
        arrayList.add(new ItemMenu("Yêu thích",R.drawable.ic_he));
        arrayList.add(new ItemMenu("Phản hồi",R.drawable.ic_f));
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
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        viewFlipper = findViewById(R.id.viewflipper);
        btnMan = (ImageButton)findViewById(R.id.imgBtManhinh);
        btnChuot = (ImageButton)findViewById(R.id.imgBtChuot);
        btnPhim = (ImageButton)findViewById(R.id.imgBtPhim);
        btnLkkhac = (ImageButton)findViewById(R.id.imgBtLkLKhac);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerlayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        listView =(ListView)findViewById(R.id.list);
        textView=(TextView)findViewById(R.id.tvToolbar);
        textView.setText(getIntent().getStringExtra("key"));


    }

}