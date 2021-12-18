package Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.linhkien.R;

public class MainActivity extends AppCompatActivity {
    EditText txtUser, txtPass;
    String DATABASE_NAME = "QLLK.db";
    SQLiteDatabase database;
    Button btnDangky, btnDangnhap, btnThoat;
    String ten, mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Controlbtn();

    }

    private void Controlbtn() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Bạn có chắc muốn thoát khỏi app?");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Hộp thoại xử lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customdialog);
                final EditText txtTk = (EditText) dialog.findViewById(R.id.editTextTk);
                final EditText txtMk = (EditText) dialog.findViewById(R.id.editTextMk);
                Button btHuy = (Button) dialog.findViewById(R.id.btnHuy);
                Button btXacnhan = (Button) dialog.findViewById(R.id.btnXacnhan);
                btXacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ten = txtTk.getText().toString().trim();
                        mk = txtMk.getText().toString().trim();

                        txtUser.setText(ten);
                        txtPass.setText(mk);

                        dialog.cancel();

                    }
                });
                btHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String name = txtUser.getText().toString();
//                String pass = txtPass.getText().toString();
//                database = Database.initDatabase(MainActivity.this, DATABASE_NAME);
//
//                Cursor cursor = database.rawQuery("select * from users where Name = " + name + " And Mk=" + pass+"", new String[]{name, pass});
//                if (cursor != null) {
//
//                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                    intent.putExtra("key", name);
//                    startActivity(intent);
//                }
                if(txtUser.getText().length() !=0 && txtPass.getText().length() != 0){
                    if(txtUser.getText().toString().equals(ten) && txtPass.getText().toString().equals(mk)){
                        Toast.makeText(MainActivity.this, "Bạn đã đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        String name = txtUser.getText().toString().trim();
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("key",name);
                        startActivity(intent);
                    }else  if(txtUser.getText().toString().equals("admin") && txtPass.getText().toString().equals("12345")){
                        Toast.makeText(MainActivity.this, "Bạn đã đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        String name = txtUser.getText().toString().trim();
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("key",name);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Bạn đã đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



        private void Anhxa () {
            txtUser = (EditText) findViewById(R.id.editTextName);
            txtPass = (EditText) findViewById(R.id.editTextpass);
            btnDangnhap = (Button) findViewById(R.id.btnDangNhap);
            btnDangky = (Button) findViewById(R.id.btnDangKy);
            btnThoat = (Button) findViewById(R.id.btnOut);
        }
    }