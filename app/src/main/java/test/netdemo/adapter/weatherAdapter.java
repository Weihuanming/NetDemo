package test.netdemo.adapter;

import android.app.Activity;
import android.icu.util.GregorianCalendar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import test.netdemo.R;
import test.netdemo.bean.ResultBean;
import test.netdemo.util.TimeUtil;

/**
 * Created by whm on 16/12/14.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;

    private final int TOPIC = 0;
    private final int INDEX = 1;
    private final int WEATHER_DATA = 2;

    private List<ResultBean> resultList;
    private List<ResultBean.WeatherBean> weatherList;
    private List<ResultBean.IndexBean> indexList;

    public WeatherAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOPIC) {
            return new WeatherTopicViewHolder(activity.getLayoutInflater().inflate(R.layout.view_topic_item, parent, false));
        } else if (viewType == INDEX) {
            return new WeatherIndexViewHolder(activity.getLayoutInflater().inflate(R.layout.view_index_item, parent, false));
        } else {
            return new WeatherForecastViewHolder(activity.getLayoutInflater().inflate(R.layout.view_weather_forecast_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WeatherTopicViewHolder) {
            initTopic((WeatherTopicViewHolder) holder);
        } else if (holder instanceof WeatherIndexViewHolder) {
            initIndex((WeatherIndexViewHolder) holder, position);
        } else {
            initWeatherForecast((WeatherForecastViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOPIC;
        } else if (position > 0 && position < 4){
            return INDEX;
        } else {
            return WEATHER_DATA;
        }
    }

    public void setResultList(List<ResultBean> resultList) {
        this.resultList = resultList;
    }

    public void setWeatherData(List<ResultBean.WeatherBean> weatherList) {
        this.weatherList = weatherList;
    }

    public void setIndex(List<ResultBean.IndexBean> indexList) {
        this.indexList = indexList;
    }

    private void initTopic(WeatherTopicViewHolder holder) {
        holder.cityname.setText(resultList.get(0).currentCity);
        holder.date.setText(weatherList.get(0).date);

        int hour = TimeUtil.getHour();
        if (hour >= 6 && hour < 18) {
            holder.weather_pic.setImageURI(weatherList.get(0).dayPictureUrl);
        } else {
            holder.weather_pic.setImageURI(weatherList.get(0).nightPictureUrl);
        }
        holder.temperature.setText(weatherList.get(0).temperature);


        holder.pm.setText("PM2.5: " + resultList.get(0).pm25);
        holder.wind.setText(weatherList.get(0).wind);
        holder.weather.setText(weatherList.get(0).weather);
    }

    private void initIndex(WeatherIndexViewHolder holder, int position) {
        //这里根据布局和API写死填充方法
        if (position == 1) {
            holder.tipt.setText(indexList.get(0).tipt);
            holder.zs.setText(indexList.get(0).zs);
            holder.tipt2.setText(indexList.get(1).tipt);
            holder.zs2.setText(indexList.get(1).zs);
        } else if (position == 2){
            holder.tipt.setText(indexList.get(2).tipt);
            holder.zs.setText(indexList.get(2).zs);
            holder.tipt2.setText(indexList.get(3).tipt);
            holder.zs2.setText(indexList.get(3).zs);
        } else if (position == 3) {
            holder.tipt.setText(indexList.get(4).tipt);
            holder.zs.setText(indexList.get(4).zs);
            holder.tipt2.setText(indexList.get(5).tipt);
            holder.zs2.setText(indexList.get(5).zs);
        }
    }

    private void initWeatherForecast(WeatherForecastViewHolder holder, int position) {
        if (position == 4) {
            holder.title.setVisibility(View.VISIBLE);
            holder.divide.setVisibility(View.VISIBLE);
        } else {
            holder.title.setVisibility(View.GONE);
            holder.divide.setVisibility(View.GONE);
        }

        holder.date.setText(weatherList.get(position - 3).date);

        int hour = TimeUtil.getHour();
        if (hour >= 6 && hour < 18) {
            holder.weather_pic.setImageURI(weatherList.get(position - 3).dayPictureUrl);
        } else {
            holder.weather_pic.setImageURI(weatherList.get(position - 3).nightPictureUrl);
        }

        holder.temperature.setText(weatherList.get(position - 3).temperature);
        holder.weather.setText(weatherList.get(position - 3).weather);
    }
}