package com.example.supertramp.dmovie.adapter.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import com.example.supertramp.dmovie.utils.ImageUtils;
import com.example.supertramp.dmovie.view.custom.RippleView;
import java.util.List;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleEntity> list;
    private OnItemClickListener clickListener;

    public ArticleAdapter(Context context, List<ArticleEntity> list)
    {
        this.context = context;
        this.list = list;
    }

    public void addList(List<ArticleEntity> list)
    {
        this.list.addAll(list);
    }

    public List<ArticleEntity> getList()
    {
        return list;
    }

    public void setOnItemClickListener(ArticleAdapter.OnItemClickListener listener)
    {
        this.clickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {

        ArticleEntity entity = list.get(position);

        ImageUtils.displayImage(entity.getImg_url(), holder.ivPic);
        holder.tvType.setText("# 影评 #");
        holder.tvTitle.setText(entity.getTitle());

        holder.rippleView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                holder.rippleView.setRippleDuration(0);
                holder.rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener()
                {
                    @Override
                    public void onComplete(RippleView rippleView)
                    {
                        rippleView.setRippleDuration(400);
                    }
                });
                if (clickListener != null)
                {
                    clickListener.onItemClick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {

        if (list != null)
        {
            return list.size();
        }
        return 0;

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        RippleView rippleView;
        ImageView ivPic;
        TextView tvTitle;
        TextView tvType;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            rippleView = (RippleView) itemView.findViewById(R.id.rippleview);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_article_pic);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_article_title);
            tvType = (TextView) itemView.findViewById(R.id.tv_article_type);
        }

    }

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

    }

}
