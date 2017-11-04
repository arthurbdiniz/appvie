package com.appvie.appvie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineModel implements Parcelable {

    private String mType;

    private String mMessage;
    private String mDate;
    private String mMedicName;
    private String mMedicCrm;
    private OrderStatus mStatus;

    public String getmMedicName() {
        return mMedicName;
    }

    public String getmMedicCrm() {
        return mMedicCrm;
    }

    public TimeLineModel() {
    }



    public TimeLineModel(String mType, String mMessage, String mDate, String mMedicName, String mMedicCrm, OrderStatus mStatus) {
        this.mType = mType;
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mMedicName = mMedicName;
        this.mMedicCrm = mMedicCrm;

        this.mStatus = mStatus;
    }

    public String getType() {
        return mType;
    }

    public String getMessage() {
        return mMessage;
    }

    public void semMessage(String message) {
        this.mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public OrderStatus getStatus() {
        return mStatus;
    }

    public void setStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
    }

    protected TimeLineModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
    }

    public static final Creator<TimeLineModel> CREATOR = new Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
}
