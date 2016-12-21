package test.netdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import test.netdemo.R;

/**
 * Created by whm on 16/12/19.
 */

public class WeatherForecastViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView date;
    public TextView temperature;
    public TextView weather;
    public View divide;
    public SimpleDraweeView weather_pic;

    public WeatherForecastViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        date = (TextView) itemView.findViewById(R.id.date);
        temperature = (TextView) itemView.findViewById(R.id.temperature);
        weather = (TextView) itemView.findViewById(R.id.weather);
        divide = itemView.findViewById(R.id.divide);
        weather_pic = (SimpleDraweeView) itemView.findViewById(R.id.weather_pic);
    }
}
