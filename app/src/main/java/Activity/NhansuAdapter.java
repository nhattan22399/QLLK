package Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.linhkien.R;

import java.util.ArrayList;

import MenuItem.Nhansu;

public class NhansuAdapter extends BaseAdapter {
    Context context;
    ArrayList<Nhansu> list;
    String DATABASE_NAME = "QLLK.db";
    SQLiteDatabase database;
    Button btnxoa,btnsua;
    ImageView imgAnh;
    TextView txtmanv,txttennv,txtcv;



    public NhansuAdapter(Context context, ArrayList<Nhansu> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_nhansu,null);

        TextView txtmanv = (TextView) row.findViewById(R.id.textViewManv);
        TextView txttennv = (TextView) row.findViewById(R.id.textViewTen);
        TextView txtcv = (TextView) row.findViewById(R.id.textViewCv);
        ImageView imgAnh = (ImageView) row.findViewById(R.id.imageViewAnh);
        Button btnsua = (Button) row.findViewById(R.id.buttonSua);
        Button btnxoa = (Button) row.findViewById(R.id.buttonXoa);



        final Nhansu nhansu = list.get(i);
        txtmanv.setText("Mã nhân viên: "+nhansu.mans+"");
        txttennv.setText(nhansu.ten);
        txtcv.setText("Chức vụ: "+nhansu.chucvu);
        Bitmap bitmap = BitmapFactory.decodeByteArray(nhansu.anh, 0, nhansu.anh.length);
        imgAnh.setImageBitmap(bitmap);

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, UpdateNhanvien.class);
               intent.putExtra("id",nhansu.mans);
               context.startActivities(new Intent[]{intent});
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xac nhan");
                builder.setMessage("Ban co muon xoa hay khong");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(nhansu.mans);
                    }

                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        return row;
    }
    private void delete(int mans){
        database = Database.initDatabase((Activity) context, DATABASE_NAME);
        database.delete("Nhansu", "MaNv = ?",new String[] {mans+""});

        Cursor cursor = database.rawQuery("Select * from Nhansu ",null);
        list.clear();
        while(cursor.moveToNext()){
            int MaNv = cursor.getInt(0);
            String TênNv = cursor.getString(1);
            String Chucvu = cursor.getString(2);
            byte[] Anhnv = cursor.getBlob(3);
            list.add( new Nhansu(MaNv,TênNv,Chucvu,Anhnv));

        }
        notifyDataSetChanged();
    }
}
