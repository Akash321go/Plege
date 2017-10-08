package com.example.advaita.plege;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] names;
    private String[] ngo;
    private String[] mode;
    private Activity context;
    ImageView bmImage;

    public CustomList(Activity context, String[] ids, String[] names, String[] ngo, String[] mode) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.ngo = ngo;
        this.mode = mode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewNGO = (TextView) listViewItem.findViewById(R.id.textViewNGO);
        TextView textViewMode = (TextView) listViewItem.findViewById(R.id.textViewMode);

        new DownloadImageTask((ImageView) listViewItem.findViewById(R.id.imageView1)).execute(ids[position]);

        textViewName.setText("Category : "+names[position]);
        textViewNGO.setText("NGO : "+ngo[position]);
        textViewMode.setText("Mode : "+mode[position]);

        return listViewItem;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}