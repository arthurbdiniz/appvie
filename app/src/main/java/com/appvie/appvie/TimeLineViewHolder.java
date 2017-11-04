package com.appvie.appvie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;



public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public  TextView mDate;
    public TextView mMessage;
    public TextView mType;
    public TextView mMedicName;
    public TextView mMedicCrm;
    public TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        mDate = (TextView)  itemView.findViewById(R.id.text_timeline_date);
        mMessage = (TextView) itemView.findViewById(R.id.text_timeline_title);
        mType = (TextView) itemView.findViewById(R.id.text_timeline_type);
        mMedicName = (TextView) itemView.findViewById(R.id.text_timeline_medic_name);
        mMedicCrm = (TextView) itemView.findViewById(R.id.text_timeline_medic_crm);
        mTimelineView.initLine(viewType);
    }





}
