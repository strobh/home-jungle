package se.bth.homejungle.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import se.bth.homejungle.R;

public class FuturePlantsAdapter extends ArrayAdapter<String> {

    private String[] plantname;
    private String[] plantdate;
    private Integer[] imgid;
    private Activity context;

    public FuturePlantsAdapter(@NonNull Activity context, String[] plantname, String[] plantdate, Integer[] imgid) {
        super(context, R.layout.future_plants_list_item, plantname);

        this.plantname = plantname;
        this.plantdate = plantdate;
        this.imgid = imgid;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        FuturePlantsAdapter.Future_View_Holder viewHolder = null;

        if(convertView==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.future_plants_list_item, null, true);
            viewHolder = new Future_View_Holder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Future_View_Holder) convertView.getTag();
        }
        viewHolder.plant_name.setText(plantname[position]);
        viewHolder.plant_date.setText(plantdate[position]);
        viewHolder.plant_img.setImageResource(imgid[position]);

        return convertView;
    }

    class Future_View_Holder {
        TextView plant_name;
        TextView plant_date;
        ImageView plant_img;

        Future_View_Holder(View v){
            plant_name = v.findViewById(R.id.tv_plant_name);
            plant_date = v.findViewById(R.id.plant_date);
            plant_img = v.findViewById(R.id.plant_img);
        }
    }
}
