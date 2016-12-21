package test.netdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import test.netdemo.R;

/**
 * Created by whm on 16/12/14.
 */

public class WeatherIndexViewHolder extends RecyclerView.ViewHolder {

    public TextView tipt;
    public TextView tipt2;
    public TextView zs;
    public TextView zs2;

    public WeatherIndexViewHolder(View itemView) {
        super(itemView);
        tipt = (TextView) itemView.findViewById(R.id.index_tipt);
        tipt2 = (TextView) itemView.findViewById(R.id.index_tipt2);
        zs = (TextView) itemView.findViewById(R.id.index_zs);
        zs2 = (TextView) itemView.findViewById(R.id.index_zs2);
    }
}