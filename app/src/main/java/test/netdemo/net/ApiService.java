package test.netdemo.net;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by whm on 16/12/13.
 */

public class ApiService implements Api{

    private static ApiService instance;

    private static Api api;

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public static Api getApi() {
        if (api == null) {
            api = initApi();
        }
        return api;
    }

    private static Api initApi() {
        String baseUrl = HOST;

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(Api.class);
    }

    @NonNull
    private <T extends RespData> Observable.Transformer<T, T> getTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .flatMap(new Func1<T, Observable<T>>() {
                            @Override
                            public Observable<T> call(final T t) {
                                return flatResponse(t);
                            }
                        });
            }
        };
    }

    @NonNull
    private <T extends RespData> Observable<T> flatResponse(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(t);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<RespData> DataList(@Path("cityname") String cityname, @Path("format") String format, @Path("key") String key) {
        return getApi().DataList(cityname, format, key).compose(this.getTransformer());
    }
}
