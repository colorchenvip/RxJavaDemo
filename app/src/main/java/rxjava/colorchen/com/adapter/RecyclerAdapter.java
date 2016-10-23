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
import rxjava.colorchen.com.model.ZhuangBiImage;

/**
 * firstFragmentAdapter
 * Created by color on 22/10/2016 23:31.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    List<ZhuangBiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FirstViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_first_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FirstViewHolder viewHolder = (FirstViewHolder) holder;
        ZhuangBiImage zhuangBiImage = images.get(position);
        Glide.with(((FirstViewHolder) holder).imageView.getContext()).load(zhuangBiImage.image_url).into(viewHolder.imageView);
        viewHolder.description.setText(zhuangBiImage.description);
    }

    @Override
    public int getItemCount() {
        return images == null ?0:images.size();
    }

    static class FirstViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageView)ImageView imageView;
        @Bind(R.id.descriptionTv)TextView description;

        public FirstViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setImages(List<ZhuangBiImage> images) {
        this.images = images;
        notifyDataSetChanged();
    }
}
