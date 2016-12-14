package test.netdemo.net;

import java.util.List;

import test.netdemo.bean.ResultBean;

/**
 * Created by whm on 16/12/12.
 */

public class RespData {

    /**
     * error : 0
     * status : success
     * date : 2016-12-12
     */

    private int error;
    private String status;
    private String date;
    private List<ResultBean> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultBean> getList() {
        return results;
    }

    public void setList(List<ResultBean> results) {
        this.results = results;
    }
}
