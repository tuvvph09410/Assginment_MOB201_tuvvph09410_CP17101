package com.example.assginment_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.assginment_mob201.Entity.ItemMenu;
import com.example.assginment_mob201.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemMenuAdapter extends BaseAdapter {
    private List<ItemMenu> itemMenuList;
    private Context context;

    public ItemMenuAdapter(List<ItemMenu> itemMenuList, Context context) {
        this.itemMenuList = itemMenuList;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.itemMenuList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.itemMenuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater;
        if (view == null) {
            viewHolder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_menu_listview, null);
            viewHolder.ivImage = view.findViewById(R.id.iv_imageItemMenu);
            viewHolder.tvTitle = view.findViewById(R.id.tv_titleItemMenu);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ItemMenu itemMenu = (ItemMenu) getItem(i);
        Picasso.get().load(itemMenu.getUrlImage())
                .placeholder(R.drawable.ic_baseline_android_24)
                .error(R.drawable.ic_baseline_report_gmailerrorred_24)
                .into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(itemMenu.getTitleItem());
        return view;
    }

    public class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
    }
}
