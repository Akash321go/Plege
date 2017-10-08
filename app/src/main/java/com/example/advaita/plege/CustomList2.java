package com.example.advaita.plege;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomList2 extends ArrayAdapter<String> {
    private String[] name;
    private String[] city;
    private String[] phone;
    private String[] email;
    private String[] desc;
    private Activity context;
    ImageView bmImage;

    public CustomList2(Activity context, String[] name, String[] city, String[] phone, String[] email, String[] desc) {
        super(context, R.layout.gethelp_list_view_layout, name);
        this.context = context;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.desc = desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.gethelp_list_view_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewCity = (TextView) listViewItem.findViewById(R.id.textViewCity);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.textViewPhone);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);

        textViewName.setText("Name : "+name[position]);
        textViewCity.setText("City : "+city[position]);
        textViewPhone.setText("Mobile : "+phone[position]);
        textViewEmail.setText("Email : "+email[position]);
        textViewDesc.setText("Description : "+desc[position]);

        return listViewItem;
    }
}