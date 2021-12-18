package Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhkien.R;

import java.util.List;

import MenuItem.ItemMenu;

public class MenuAdapter  extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemMenu> lst;

    public MenuAdapter(Context context, int layout, List<ItemMenu> lst) {
        this.context = context;
        this.layout = layout;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView textView;
        ImageView imgv;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.textView =(TextView)convertView.findViewById(R.id.tv);
            viewHolder.imgv = (ImageView)convertView.findViewById(R.id.imgicon);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(lst.get(i).tenItem);
        viewHolder.imgv.setImageResource(lst.get(i).icon);

        return convertView;
    }
}
