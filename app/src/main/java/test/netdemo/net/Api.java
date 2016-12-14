package test.netdemo.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by whm on 16/12/12.
 */

public interface Api {

    String HOST = "http://api.map.baidu.com/";

    //http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=11ffd27d38deda622f51c9d314d46b17
    //location={城市名}&output={返回格式}&ak={百度AK}

    @GET("telematics/v3/weather")
    Observable<RespData> DataList(@Query("location") String cityname, @Query("output") String format, @Query("ak") String key);
}
