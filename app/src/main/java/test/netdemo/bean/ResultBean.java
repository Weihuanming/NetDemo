package test.netdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by whm on 16/12/12.
 */

public class ResultBean implements Parcelable {

    public String currentCity;

    public String pm25;

    public List<IndexBean> index;

    public List<WeatherBean> weather_data;

    public static class IndexBean implements Parcelable {

        public String title;

        public String zs;

        public String tipt;

        public String des;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.zs);
            dest.writeString(this.tipt);
            dest.writeString(this.des);
        }

        public IndexBean() {
        }

        protected IndexBean(Parcel in) {
            this.title = in.readString();
            this.zs = in.readString();
            this.tipt = in.readString();
            this.des = in.readString();
        }

        public static final Parcelable.Creator<IndexBean> CREATOR = new Parcelable.Creator<IndexBean>() {
            @Override
            public IndexBean createFromParcel(Parcel source) {
                return new IndexBean(source);
            }

            @Override
            public IndexBean[] newArray(int size) {
                return new IndexBean[size];
            }
        };
    }

    public static class WeatherBean implements Parcelable {
        public String date;

        public String dayPictureUrl;

        public String nightPictureUrl;

        public String weather;

        public String wind;

        public String temperature;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.date);
            dest.writeString(this.dayPictureUrl);
            dest.writeString(this.nightPictureUrl);
            dest.writeString(this.weather);
            dest.writeString(this.wind);
            dest.writeString(this.temperature);
        }

        public WeatherBean() {
        }

        protected WeatherBean(Parcel in) {
            this.date = in.readString();
            this.dayPictureUrl = in.readString();
            this.nightPictureUrl = in.readString();
            this.weather = in.readString();
            this.wind = in.readString();
            this.temperature = in.readString();
        }

        public static final Parcelable.Creator<WeatherBean> CREATOR = new Parcelable.Creator<WeatherBean>() {
            @Override
            public WeatherBean createFromParcel(Parcel source) {
                return new WeatherBean(source);
            }

            @Override
            public WeatherBean[] newArray(int size) {
                return new WeatherBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currentCity);
        dest.writeString(this.pm25);
        dest.writeTypedList(this.index);
        dest.writeTypedList(this.weather_data);
    }

    public ResultBean() {
    }

    protected ResultBean(Parcel in) {
        this.currentCity = in.readString();
        this.pm25 = in.readString();
        this.index = in.createTypedArrayList(IndexBean.CREATOR);
        this.weather_data = in.createTypedArrayList(WeatherBean.CREATOR);
    }

    public static final Parcelable.Creator<ResultBean> CREATOR = new Parcelable.Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel source) {
            return new ResultBean(source);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };
}
