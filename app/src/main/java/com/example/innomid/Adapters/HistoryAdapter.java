package com.example.innomid.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Data.CurrentData;
import com.example.innomid.Data.HistoryData;
import com.example.innomid.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>  {


    private Context context;

    public HistoryAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.history_list_iteem,parent,false);
        return new HistoryViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
                SharedPreferences mySharedPreferences = context.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
                String username = mySharedPreferences.getString("cancel", "name");
                holder.drname.setText(username);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {


        TextView drname;


        public HistoryViewHolder(View itemView) {
            super(itemView);
            drname=itemView.findViewById(R.id.drnc);




        }
    }

}
