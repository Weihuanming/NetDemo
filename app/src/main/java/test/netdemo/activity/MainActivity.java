package test.netdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import test.netdemo.R;
import test.netdemo.bean.ResultBean;
import test.netdemo.net.ApiService;
import test.netdemo.net.RespData;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "11ffd27d38deda622f51c9d314d46b17";

    private final String format = "json";

    private List<ResultBean> list = new ArrayList<>();

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initView() {

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
            }
        });
        compositeSubscription.add(subscription);
    }

    private void bindData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}