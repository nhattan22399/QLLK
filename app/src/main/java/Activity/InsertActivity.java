package Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.linhkien.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InsertActivity extends AppCompatActivity {
    EditText txtTenNv,  txtChucvu, manv;

    Button btnChonhinh, btnChuphinh, btnLuu,btnHuy;
    ImageView imgAnhsua;
    String DATABASE_NAME = "QLLK.db";
    SQLiteDatabase database;

    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_nhanvien);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnChuphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
        btnChonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }

    private void insert() {
        String maNv = manv.getText().toString();
        String tenNv =  txtTenNv.getText().toString();
        String chucvu =  txtChucvu.getText().toString();
        byte[] anhsua = getByteArrayFromImageView(imgAnhsua);

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaNv",maNv);
        contentValues.put("TênNv",tenNv);
        contentValues.put("Chucvu",chucvu);
        contentValues.put("Anhnv",anhsua);
        database = Database.initDatabase(InsertActivity.this, DATABASE_NAME);
        database.insert("Nhansu",null,contentValues);
        Intent intent = new Intent(InsertActivity.this,Main3Activity.class);
        startActivity(intent);

    }
    public byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }



    private void addControl() {
        manv =(EditText)findViewById(R.id.editTextMansThem);
        txtTenNv = (EditText)findViewById(R.id.editTextTennvThem);
        txtChucvu = (EditText)findViewById(R.id.editTextchucvuThem);
        btnChonhinh = (Button)findViewById(R.id.buttonChonHinhThem);
        btnChuphinh = (Button)findViewById(R.id.buttonChupHinhThem);
        btnLuu = (Button)findViewById(R.id.buttonLuuThem);
        btnHuy = (Button)findViewById(R.id.buttonHuyThem);
        imgAnhsua = (ImageView)findViewById(R.id.imageAnhThem);
    }
    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgAnhsua.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgAnhsua.setImageBitmap(bitmap);
            }
        }

        }
    }