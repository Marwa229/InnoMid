package com.example.innomid.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Data.Result;
import com.example.innomid.R;
import com.example.innomid.Utils.HospitalUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

import static android.content.Context.MODE_PRIVATE;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.RecipeViewHolder> implements Filterable {

    private Context context;
    private List<Result> resultList;
    private List<Result> contactListFiltered;

    private Intent intent;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    public HospitalsAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
        this.contactListFiltered=resultList;
        sharedPreferences = context.getSharedPreferences(HospitalUtils.SHARED_PREFERENCES,
                MODE_PRIVATE);

    }




    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospital_list_item, parent, false);
        return new RecipeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        holder.dishText.setText(contactListFiltered.get(position).getName());
        String imageUrl = resultList.get(position).getImage();
        if (!imageUrl.equals("")) {
            //Load image if present
            Picasso.with(context).load(imageUrl).into(holder.imageView);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                    //  ((Activity)context).finish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = resultList;
                } else {
                    List<Result> filteredList = new ArrayList<>();
                    for (Result row : resultList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
    }



        @Override
        protected void publishResults(CharSequence charSequence, Filter.FilterResults
        filterResults) {
            contactListFiltered = (ArrayList<Result>) filterResults.values;
            notifyDataSetChanged();
        }
    };}

    public class RecipeViewHolder extends RecyclerView.ViewHolder {


        TextView dishText;
        CardView cardView;
        ImageView imageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            dishText=itemView.findViewById(R.id.hospital_text);
            cardView=itemView.findViewById(R.id.card_view);



        }
    }
}
