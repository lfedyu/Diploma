package com.fedyushko.lilia.p0031_firstprogect;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class Place implements Parcelable {

    private static final String TAG = "myLogs";

    float x, y;
    String name;
    String info;
    String longInfo;
    int photoId;
    int bigPhotoId;
    int addId;



    Place(String name, String info, int photoId,int bigPhotoId, float x, float y) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.info = info;
        this.photoId = photoId;
        this.bigPhotoId = bigPhotoId;
        addId=R.drawable.ic_add;

    }

    public String getLongInfo() {
        return longInfo;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getBigPhotoId() {
        return bigPhotoId;
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return (float) x;
    }

    public float getY() {
        return (float) y;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }


    public void setLongInfo(String longInfo) {
        this.longInfo = longInfo;
    }

    public double distance(Place pt) {
        double px = pt.getX() - this.getX();
        double py = pt.getY() - this.getY();
        return Math.sqrt(px * px + py * py);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // конструктор, считывающий данные из Parcel
    private Place(Parcel parcel) {
        photoId = parcel.readInt();
        bigPhotoId = parcel.readInt();
        x = parcel.readFloat();
        y = parcel.readFloat();
        name = parcel.readString();
        longInfo = parcel.readString();
    }


    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        // распаковываем объект из Parcel
        public Place createFromParcel(Parcel in) {
            Log.d(TAG, "createFromParcel");
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place [size];
        }
    };

//треба всі поля)))

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getPhotoId());
        parcel.writeInt(getBigPhotoId());
        parcel.writeFloat(getX());
        parcel.writeFloat(getY());
        parcel.writeString(getName());
        parcel.writeString(getLongInfo());
    }
}
