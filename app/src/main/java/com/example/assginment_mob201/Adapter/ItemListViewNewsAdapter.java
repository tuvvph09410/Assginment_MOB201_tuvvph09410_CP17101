package com.example.assginment_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assginment_mob201.Entity.NewXML;
import com.example.assginment_mob201.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemListViewNewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewXML> newXMLList;

    public ItemListViewNewsAdapter(Context context, List<NewXML> newXMLList) {
        this.context = context;
        this.newXMLList = newXMLList;
    }

    @Override
    public int getCount() {
        if (this.newXMLList == null) {
            return 0;
        }
        return this.newXMLList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.newXMLList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview_news, null);
//            viewHolder.ivNews = convertView.findViewById(R.id.iv_urlImageNews);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_titleNew);
            viewHolder.tvDescription = convertView.findViewById(R.id.tv_descriptionNews);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewXML newXML = (NewXML) getItem(position);
        viewHolder.tvTitle.setText(newXML.getTitle());
        viewHolder.tvDescription.setText(newXML.getPubDate());
//        Picasso.get().load(newXML.getUrlImage())
//                .placeholder(R.drawable.ic_baseline_android_24)
//                .error(R.drawable.ic_baseline_error_outline_24)
//                .into(viewHolder.ivNews);

        return convertView;
    }

    public class ViewHolder {
        TextView tvTitle, tvDescription;
//        ImageView ivNews;
    }
}
