package read.bookmine;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 06-06-2016.
 */
public class FileFragment extends ListFragment {

    private File currentDir;
    private FileAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File("storage/");
        fill(currentDir);
    }
    private void fill(File f)
    {
        File[]dirs = f.listFiles();
        getActivity().setTitle("Current Dir: " + f.getName());
        List<FileBean> dir = new ArrayList<FileBean>();
        List<FileBean> fls = new ArrayList<FileBean>();
        try{
            for(File ff: dirs)
            {
                Date lastModDate = new Date(ff.lastModified());
                DateFormat formater = DateFormat.getDateTimeInstance();
                String date_modify = formater.format(lastModDate);
                if(ff.isDirectory()){


                    File[] fbuf = ff.listFiles();
                    int buf = 0;
                    if(fbuf != null){
                        buf = fbuf.length;
                    }
                    else buf = 0;
                    String num_item = String.valueOf(buf);
                    if(buf == 0) num_item = num_item + " item";
                    else num_item = num_item + " items";

                    //String formated = lastModDate.toString();
                    dir.add(new FileBean(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),"directory_icon",ff));
                }
                else
                {
                    fls.add(new FileBean(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"file_icon",ff));
                }
            }
        }catch(Exception e)
        {

        }
        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if(!f.getName().equalsIgnoreCase("sdcard"))
            dir.add(0,new FileBean("...","Parent Directory","",f.getParent(),"directory_up",f));
        adapter = new FileAdapter(getActivity(),R.layout.files,dir);
        this.setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        FileBean o = adapter.getItem(position);
        Log.e("o.getImage()==",o.getImage()+"   "+o.getPath());
        if(o.getImage().equalsIgnoreCase("directory_icon")||o.getImage().equalsIgnoreCase("directory_up")){
            if ((o.getPath()!=null) && !(o.getPath().equals("/"))) {
                   Log.e("Secnd if",o.getPath());
                    currentDir = new File(o.getPath());
                    fill(currentDir);
            }
        }
        else
        {
            onFileClick(o);
        }
    }
    private void onFileClick(FileBean o)
    {
        //Toast.makeText(this, "Folder Clicked: "+ currentDir, Toast.LENGTH_SHORT).show();
       /* Intent intent = new Intent();
        intent.putExtra("GetPath", currentDir.toString());
        intent.putExtra("GetFileName", o.getName());
        getActivity().setResult(getActivity().RESULT_OK, intent);
        getActivity().finish();*/
        Toast.makeText(getActivity(),o.getPath(), Toast.LENGTH_SHORT).show();
    }
}