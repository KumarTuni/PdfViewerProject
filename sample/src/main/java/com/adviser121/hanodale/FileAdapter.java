package com.adviser121.hanodale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekinarslan.sample.R;

import java.util.List;

/**
 * Created by Administrator on 06-06-2016.
 */
public class FileAdapter extends ArrayAdapter<FileBean> {

    private Context c;
    private int id;
    private List<FileBean> items;

    public FileAdapter(Context context, int textViewResourceId,
                       List<FileBean> objects) {
        super(context, textViewResourceId, objects);
        c = context;
        id = textViewResourceId;
        items = objects;
    }
    public FileBean getItem(int i)
    {
        return items.get(i);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }

               /* create a new view of my layout and inflate it in the row */
        //convertView = ( RelativeLayout ) inflater.inflate( resource, null );

        final FileBean o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) v.findViewById(R.id.TextView01);
            TextView t2 = (TextView) v.findViewById(R.id.TextView02);
            TextView t3 = (TextView) v.findViewById(R.id.TextViewDate);
                       /* Take the ImageView from layout and set the city's image */
            ImageView imageCity = (ImageView) v.findViewById(R.id.fd_Icon1);
            String uri = "drawable/" + o.getImage();
            Log.v("uri==", uri);
            /*int imageResource = c.getResources().getIdentifier(uri, null, c.getPackageName());
            Toast.makeText(c,imageResource,Toast.LENGTH_SHORT).show();
            Drawable image = c.getResources().getDrawable(imageResource);*/
            if(items.get(position).getFilee().isFile())
                imageCity.setImageResource(R.drawable.file);

            else
                imageCity.setImageResource(R.drawable.folder);


            if(t1!=null)
                t1.setText(o.getName());
            if(t2!=null)
                t2.setText(o.getData());
            if(t3!=null)
                t3.setText(o.getDate());
        }
        return v;
    }
}
