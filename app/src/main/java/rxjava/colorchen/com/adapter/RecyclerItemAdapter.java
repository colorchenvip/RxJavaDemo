package rxjava.colorchen.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxjava.colorchen.com.R;
import rxjava.colorchen.com.model.Item;

/**
 * Created by color on 23/10/2016 11:52.
 */

public class RecyclerItemAdapter extends RecyclerView.Adapter {
    List<Item> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_map_item,parent,false);
        return new MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MapViewHolder viewHolder = (MapViewHolder) holder;
        Item item = images.get(position);
        Glide.with(holder.itemView.getContext()).load(item.imageUrl)
                .into(viewHolder.imageIv);
        viewHolder.descriptionTv.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0:images.size();
    }

    public void setImages(List<Item> images){
        this.images = images;
        notifyDataSetChanged();
    }

    static class MapViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageIv_map)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public MapViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
