package com.example.innomid.Adapters;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Data.MedicineData;
import com.example.innomid.R;
import com.example.innomid.Utils.HospitalUtils;
import com.google.gson.Gson;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {


    private Context context;
    private List<MedicineData> resultList;

    private Intent intent;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    public MedicineAdapter(Context context, List<MedicineData> resultList) {
        this.context = context;
        this.resultList = resultList;
        sharedPreferences = context.getSharedPreferences(HospitalUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root= LayoutInflater.from(context).inflate(R.layout.medicine_list_parent_item,parent,false);
        return new MedicineViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        holder.drNametxt.setText(resultList.get(position).getDrName());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {


        TextView drNametxt;
        TextView Specialize;
        TextView Date;
        TextView Time;
        TextView diseaseNane;
        TextView medicineName;
        TextView medicineTime;
        TextView medicineDose;
        CardView cardView;
        ImageView imageView;
        ImageView imageView2;
        View body;

        public MedicineViewHolder(View itemView) {
            super(itemView);
            drNametxt=itemView.findViewById(R.id.drnf);
            cardView=itemView.findViewById(R.id.card);
            ImageView imageView=itemView.findViewById(R.id.drf);
            this.imageView2=itemView.findViewById(R.id.collapse);
            this.body=itemView.findViewById(R.id.body);
            RelativeLayout layout=itemView.findViewById(R.id.cardcont);


            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    body.setVisibility(body.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                    if(body.getVisibility()==View.VISIBLE){
                        imageView2.setImageResource(R.drawable.arrowupcurrentbtn);
                    }

                    if(body.getVisibility()==View.GONE){
                        imageView2.setImageResource(R.drawable.arrowdowncurrentbtn);

                    }

                }
            });
            cardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layout.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);


        }
    }

}
