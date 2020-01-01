package com.example.innomid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Activities.SelectDateAndTimeActivity;
import com.example.innomid.Data.DrnameData;
import com.example.innomid.Fragments.FragmentDrDetail;
import com.example.innomid.R;
import com.example.innomid.Utils.LaboratoryUtils;

import java.util.ArrayList;
import java.util.List;

public class DrAdapter extends  RecyclerView.Adapter<DrAdapter.DrViewHolder> implements Filterable {


    private Context context;
    private List<DrnameData> resultList;
    private SharedPreferences sharedPreferences;
    private List<DrnameData> resultfilter;

    public DrAdapter(Context context, List<DrnameData> resultList) {
        this.context = context;
        this.resultList = resultList;
        this.resultfilter=resultList;
        sharedPreferences = context.getSharedPreferences(LaboratoryUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public DrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.drname_list_item,parent,false);
        return new DrViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DrViewHolder holder, int position) {
        holder.drname.setText(resultfilter.get(position).getDrname());
        holder.Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SelectDateAndTimeActivity.class);
                context.startActivity(intent);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new FragmentDrDetail();
                loadFragment(fragment);

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultfilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    resultfilter=resultList;
                } else {
                    List<DrnameData> filteredList = new ArrayList<>();
                    for (DrnameData row : resultList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDrname().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    resultfilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resultfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                resultfilter= (ArrayList<DrnameData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DrViewHolder extends RecyclerView.ViewHolder {
        TextView drname;
        TextView specialnamme;
        TextView detail;
        LinearLayout Book;
        TextView time;
        Button imageButton;
        CardView cardView;

        public DrViewHolder(View itemView) {
            super(itemView);
            drname=itemView.findViewById(R.id.dr_name_txt);
            specialnamme=itemView.findViewById(R.id.dr_job_txt);
            detail=itemView.findViewById(R.id.dr_detail_txt);
            Book=itemView.findViewById(R.id.book);
            cardView=itemView.findViewById(R.id.dr_name_cardview);

        }
    }

    public  void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        // transaction.addToBackStack(null);
        ((AppCompatActivity)context).getFragmentManager().popBackStack();
        MainActivity.navigation.getMenu().findItem(R.id.navigation_home).setChecked(false);

        //transaction.addToBackStack("TAG");
        transaction.commit();
    }


}
