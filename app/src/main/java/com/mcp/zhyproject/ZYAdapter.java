package com.mcp.zhyproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2021/12/21  17:44.
 * description :
 **/
public class ZYAdapter extends RecyclerView.Adapter<ZYItemViewHolder> {

    List<String> data = new ArrayList<>();
    public ZYAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ZYItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZYItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(ZYItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class ZYItemViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public ZYItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }


}
