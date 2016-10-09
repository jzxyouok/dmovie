package com.example.supertramp.dmovie.adapter.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.utils.CommonUtils;
import com.example.supertramp.dmovie.utils.ImageUtils;
import com.example.supertramp.dmovie.utils.StringUtils;
import com.example.supertramp.dmovie.view.custom.RippleView;

import java.util.List;

/**
 * Created by supertramp on 16/7/23.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context context;
    private List<BriefVideoEntity> list;

    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    private float lastX;
    private float lastY;
    private long lastDownTime;
    private long TIME_PRESS_LONG = 50;
    private boolean isLongPressed;

    public VideoAdapter(Context context, List<BriefVideoEntity> list)
    {
        this.context = context;
        this.list = list;
    }

    public void addList(List<BriefVideoEntity> list)
    {
        this.list.addAll(list);
    }

    public List<BriefVideoEntity> getList()
    {
        return list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BriefVideoEntity entity = list.get(position);

        if (StringUtils.isEmpty(entity.getCates().get(0).getCateid()))
        {
            ImageUtils.displayImageFromLocal(entity.getImage(), holder.ivPic);
        }
        else
        {
            ImageUtils.displayImage(entity.getImage(), holder.ivPic);
        }

        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvCategory.setText(list.get(position).getCates().get(0).getCatename() + " " + CommonUtils.toFormatTime(list.get(position).getDuration()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.longClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        RippleView rippleView;
        ImageView ivPic;
        TextView tvSubject;
        TextView tvTitle;
        TextView tvCategory;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            rippleView = (RippleView) itemView.findViewById(R.id.item_ripple);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_item_pic);
            tvSubject = (TextView) itemView.findViewById(R.id.tv_item_subject);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_item_category);

            rippleView.setOnClickListener(this);
            //itemView.setOnTouchListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
            {
                rippleView.setRippleDuration(0);
                rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {
                        rippleView.setRippleDuration(400);
                    }
                });
                clickListener.onItemClick(v, getPosition());
            }
        }

        /*@Override
        public boolean onTouch(View v, MotionEvent event)
        {

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    lastX = event.getX();
                    lastY = event.getY();
                    lastDownTime = event.getDownTime();
                    break;
                case MotionEvent.ACTION_MOVE:
                    switch (isLongPress(event.getX(), event.getY(), event.getEventTime()))
                    {
                        case 1:
                            if (longClickListener != null && !isLongPressed)
                            {
                                longClickListener.onItemLongClick(v);
                                isLongPressed = true;
                            }
                            break;
                        case 2:
                            longClickListener.complete(v);
                            isLongPressed = false;
                            break;
                        default:
                            if (isLongPressed)
                            {
                                longClickListener.complete(v);
                                isLongPressed = false;
                            }
                            break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    longClickListener.complete(v);
                    isLongPressed = false;
                    break;
                default:break;
            }

            return false;
        }*/
    }

    private int isLongPress(float thisX, float thisY, long thisEventTime)
    {
        float offsetX = Math.abs(thisX - lastX);
        float offsetY = Math.abs(thisY - lastY);
        long intervalTime = thisEventTime - lastDownTime;
        if (offsetX <= 20 && offsetY <= 5 && intervalTime >= TIME_PRESS_LONG)
        {
            return 1;
        }
        else if (offsetX > 20 || offsetY > 5)
        {
            return 2;
        }
        return 3;
    }

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

    }

    public interface OnItemLongClickListener {

        public void onItemLongClick(View view);

        public void complete(View view);

    }

}
