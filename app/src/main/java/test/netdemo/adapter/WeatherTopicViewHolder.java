package test.netdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import test.netdemo.R;

/**
 * Created by whm on 16/12/19.
 */

public class WeatherTopicViewHolder extends RecyclerView.ViewHolder {

    public TextView cityname;
    public TextView date;
    public TextView temperature;
    public TextView pm;
    public TextView wind;
    public TextView weather;
    public SimpleDraweeView weather_pic;

    public WeatherTopicViewHolder(View itemView) {
        super(itemView);
        cityname = (TextView) itemView.findViewById(R.id.cityname);
        date = (TextView) itemView.findViewById(R.id.date);
        temperature = (TextView) itemView.findViewById(R.id.temperature);
        pm = (TextView) itemView.findViewById(R.id.pm);
        weather = (TextView) itemView.findViewById(R.id.topic_weather);
        wind = (TextView) itemView.findViewById(R.id.wind);
        weather_pic = (SimpleDraweeView) itemView.findViewById(R.id.topic_weather_pic);
    }
}
