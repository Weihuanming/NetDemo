package test.netdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import test.netdemo.R;
import test.netdemo.adapter.WeatherAdapter;
import test.netdemo.bean.ResultBean;
import test.netdemo.net.ApiService;
import test.netdemo.net.RespData;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "11ffd27d38deda622f51c9d314d46b17";

    private final String format = "json";

    private List<ResultBean> list = new ArrayList<>();

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private RecyclerView weatherList;

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        weatherList = (RecyclerView) findViewById(R.id.weather_list);
    }

    private void initData() {
        Subscription subscription = ApiService.getInstance().DataList("北京", format, KEY).subscribe(new Subscriber<RespData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.toString();
            }

            @Override
            public void onNext(RespData respData) {
                list.addAll(respData.getList());
                bindData();
            }
        });
        compositeSubscription.add(subscription);
    }

    private void bindData() {
        weatherList.setLayoutManager(new LinearLayoutManager(this));
        weatherAdapter = new WeatherAdapter(this);
        weatherAdapter.setPM(list.get(0).pm25);
        weatherAdapter.setWeatherData(list.get(0).weather_data);
        weatherAdapter.setIndex(list.get(0).index);
        weatherList.setAdapter(weatherAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}