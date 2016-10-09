package com.example.supertramp.dmovie.adapter.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.adapter.listener.OnItemClickListener;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.utils.CommonUtils;
import com.example.supertramp.dmovie.utils.ImageUtils;
import java.util.List;

/**
 * Created by supertramp on 16/8/1.
 */
public class SearchVideoAdapter extends RecyclerView.Adapter<SearchVideoAdapter.MyViewHolder> {

    private Context context;
    private List<BriefVideoEntity> list;
    private OnItemClickListener listener;

    public SearchVideoAdapter(Context context, List<BriefVideoEntity> list)
    {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public void addList(List<BriefVideoEntity> list)
    {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video_search, null));
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BriefVideoEntity entity = list.get(position);

        ImageUtils.displayImage(entity.getImage(), holder.ivPic);
        holder.tvTime.setText(CommonUtils.toFormatTime(entity.getDuration()));
        holder.tvTitle.setText(entity.getTitle());
        holder.tvPoint.setText(entity.getRating() + "分");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPic;
        TextView tvTime;
        TextView tvTitle;
        TextView tvPoint;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            ivPic = (ImageView) itemView.findViewById(R.id.iv_search_item_pic);
            tvTime = (TextView) itemView.findViewById(R.id.tv_search_item_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_search_item_title);
            tvPoint = (TextView) itemView.findViewById(R.id.tv_search_item_point);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (listener != null)
                    {
                        listener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

    }

}
