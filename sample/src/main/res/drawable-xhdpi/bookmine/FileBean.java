package read.bookmine;

import java.io.File;

/**
 * Created by Administrator on 06-06-2016.
 */
public class FileBean implements Comparable<read.bookmine.FileBean> {
    private String name;
    private String data;
    private String date;
    private String path;
    private String image;

    public File getFilee() {
        return filee;
    }
    private File filee;

    public FileBean(String n, String d, String dt, String p, String img, File ff)
    {
        name = n;
        data = d;
        date = dt;
        path = p;
        image = img;
        filee=ff;
    }
    public String getName()
    {
        return name;
    }
    public String getData()
    {
        return data;
    }
    public String getDate()
    {
        return date;
    }
    public String getPath()
    {
        return path;
    }
    public String getImage() {
        return image;
    }
    public int compareTo(read.bookmine.FileBean o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}
