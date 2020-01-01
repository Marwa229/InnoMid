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
import com.example.innomid.Data.LaboratoryData;
import com.example.innomid.R;
import com.example.innomid.Utils.LaboratoryUtils;

import java.util.List;

public class LaboratoryAdapter extends RecyclerView.Adapter<LaboratoryAdapter.LaboratoryViewHolder>{

    private Context context;
    private List<LaboratoryData> resultList;
    private SharedPreferences sharedPreferences;

    public LaboratoryAdapter(Context context, List<LaboratoryData> resultList) {
        this.context = context;
        this.resultList = resultList;
        sharedPreferences = context.getSharedPreferences(LaboratoryUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public LaboratoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.laboratory_list_item,parent,false);
        return new LaboratoryViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull LaboratoryViewHolder holder, int position) {
        holder.labname.setText(resultList.get(position).getLabname());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LaboratoryResultActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount(){
        return resultList.size();
    }

    public class LaboratoryViewHolder extends RecyclerView.ViewHolder {
        TextView labname;
        TextView drname;
        TextView specialnamme;
        TextView date;
        TextView time;
        ImageButton imageButton;

        public LaboratoryViewHolder(View itemView) {
            super(itemView);
            labname=itemView.findViewById(R.id.raditit);
            drname=itemView.findViewById(R.id.labnf);
            specialnamme=itemView.findViewById(R.id.labtit);
            date=itemView.findViewById(R.id.djt);
            time=itemView.findViewById(R.id.dttxt1);
            imageButton=itemView.findViewById(R.id.openarrow);

        }
    }

}
