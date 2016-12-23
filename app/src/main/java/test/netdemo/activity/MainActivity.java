package test.netdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

    private Toolbar toolbar;

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData("北京");
    }

    private void initView() {
        weatherList = (RecyclerView) findViewById(R.id.weather_list);
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.change_city);
        toolbar.inflateMenu(R.menu.city_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beijing:
                        list.clear();
                        initData("北京");
                        return true;
                    case R.id.xian:
                        list.clear();
                        initData("西安");
                        return true;
                    case R.id.lanzhou:
                        list.clear();
                        initData("兰州");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void initData(String city) {
        Subscription subscription = ApiService.getInstance().DataList(city, format, KEY).subscribe(new Subscriber<RespData>() {
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
        weatherAdapter.setResultList(list);
        weatherAdapter.setWeatherData(list.get(0).weather_data);
        weatherAdapter.setIndex(list.get(0).index);
        weatherList.setAdapter(weatherAdapter);
        weatherAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}