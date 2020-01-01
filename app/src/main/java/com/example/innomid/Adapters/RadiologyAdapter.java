package com.example.innomid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innomid.Activities.LaboratoryResultActivity;
import com.example.innomid.Activities.RadiologyResultActivity;
import com.example.innomid.Data.RaiologyData;
import com.example.innomid.R;
import com.example.innomid.Utils.RadiologyUtils;

import java.util.List;

public class RadiologyAdapter extends RecyclerView.Adapter<RadiologyAdapter.RadiologyViewHolder> {


    private Context context;
    private List<RaiologyData> resultList;
    private SharedPreferences sharedPreferences;

    public RadiologyAdapter(Context context, List<RaiologyData> resultList) {
        this.context = context;
        this.resultList = resultList;
        sharedPreferences = context.getSharedPreferences(RadiologyUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RadiologyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.radiology_list_item, parent, false);
        return new RadiologyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RadiologyViewHolder holder, int position) {
        holder.radname.setText(resultList.get(position).getRadioname());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RadiologyResultActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class RadiologyViewHolder extends RecyclerView.ViewHolder {
        TextView radname;
        TextView drname;
        TextView specialnamme;
        TextView date;
        TextView time;
        ImageButton imageButton;

        public RadiologyViewHolder(View itemView) {
            super(itemView);
            radname = itemView.findViewById(R.id.raditit);
            drname = itemView.findViewById(R.id.drnf);
            specialnamme = itemView.findViewById(R.id.jobf);
            date = itemView.findViewById(R.id.djt);
            time = itemView.findViewById(R.id.dttxt1);
            imageButton = itemView.findViewById(R.id.radbotton);

        }
    }

}
