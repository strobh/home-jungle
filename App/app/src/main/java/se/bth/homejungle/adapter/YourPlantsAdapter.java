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

import org.w3c.dom.Text;

import se.bth.homejungle.R;


public class YourPlantsAdapter extends ArrayAdapter<String> {

    private String[] plantname;
    private String[] plantdesc;
    private String[] water_time;
    private String[] water_amount;
    private Integer[] imgid;
    private Activity context;

    public YourPlantsAdapter(@NonNull Activity context, String[] plantname, String[] plantdesc, String[] water_amount, String[] water_time, Integer[] imgid) {
        super(context, R.layout.your_plants_list_item, plantname);

        this.context = context;
        this.plantname = plantname;
        this.plantdesc = plantdesc;
        this.water_amount = water_amount;
        this.water_time = water_time;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View_Holder viewHolder = null;
        if(convertView==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.your_plants_list_item, null, true);
            viewHolder = new View_Holder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (View_Holder) convertView.getTag();
        }
        viewHolder.plant_name.setText(plantname[position]);
        viewHolder.plant_desc.setText(plantdesc[position]);
        viewHolder.water_amount.setText(water_amount[position]);
        viewHolder.water_time.setText(water_time[position]);
        viewHolder.plant_img.setImageResource(imgid[position]);

        return convertView;
    }

    class View_Holder {
        TextView plant_name;
        TextView plant_desc;
        TextView water_amount;
        TextView water_time;
        ImageView plant_img;


        View_Holder(View v){
            plant_name = v.findViewById(R.id.tv_plant_name);
            plant_desc = v.findViewById(R.id.tv_plant_desc);
            water_amount = v.findViewById(R.id.tv_water_amount);
            water_time = v.findViewById(R.id.tv_water_time);
            plant_img = v.findViewById(R.id.plant_img);
        }
    }
}
