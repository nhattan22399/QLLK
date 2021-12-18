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

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ImageButton btnNs, btnLk;
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    ListView listView;
    ArrayList<ItemMenu> arrayList;
    MenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AnhXa();
        ActionBar();
        ActionViewFliper();
        ActionMenu();
        ActionBtn();


    }

    private void ActionBtn() {
        btnLk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });

        btnNs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textView.getText().toString().trim();
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("key1",name);
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
        manganh.add("https://imgproxy.k7.tinhte.vn/0Wnc9NVEnwviqjOaMep8iLiSHG3ELqEyHKkODao9F38/h:460/plain/https://photo2.tinhte.vn/data/attachment-files/2021/08/5577318_og.jpg");
        manganh.add("https://imgproxy.k7.tinhte.vn/ejgkuCWbmeM3IpRlbgsvETj551UfSSnbtlnUkWVcT1M/h:460/plain/https://photo2.tinhte.vn/data/attachment-files/2021/02/5340116_og.jpg");
        manganh.add("https://imgproxy.k7.tinhte.vn/obB-j_U4vH-9eOc49y9PriucPDsKzvZ3-FaD2TvdDRM/h:232/plain/https://photo2.tinhte.vn/data/attachment-files/2021/10/5669644_og.jpg");
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
        btnNs = (ImageButton)findViewById(R.id.imgBtNv);
        btnLk = (ImageButton)findViewById(R.id.imgBtLk);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerlayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        listView =(ListView)findViewById(R.id.list);
        textView=(TextView)findViewById(R.id.tvToolbar);
        textView.setText(getIntent().getStringExtra("key"));


    }

}
