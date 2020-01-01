package com.example.innomid.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innomid.Data.SpecialityData;
import com.example.innomid.R;
import com.example.innomid.Utils.LaboratoryUtils;
import java.util.ArrayList;
import java.util.List;

public class SpecialityAdapter  extends  RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder> implements Filterable {


    private Context context;
    private List<SpecialityData> resultList;
    private SharedPreferences sharedPreferences;
    private List<SpecialityData> resultfilter;


    public SpecialityAdapter(Context context, List<SpecialityData> resultList) {
        this.context = context;
        this.resultList = resultList;
        this.resultfilter=resultList;
        sharedPreferences = context.getSharedPreferences(LaboratoryUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    resultfilter = resultList;
                } else {
                    List<SpecialityData> filteredList = new ArrayList<>();
                    for (SpecialityData row : resultList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getSpeeciality().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    resultfilter= filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resultfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                resultfilter = (ArrayList<SpecialityData>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    @NonNull
    @Override
    public SpecialityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.speciality_list_item,parent,false);
        return new SpecialityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialityViewHolder holder, int position) {
        holder.specname.setText(resultfilter.get(position).getSpeeciality());

    }

    @Override
    public int getItemCount() {
        return resultfilter.size();
    }

    public class SpecialityViewHolder extends RecyclerView.ViewHolder {
        TextView specname;
        ImageView img;
        RelativeLayout container;

        public SpecialityViewHolder(View itemView) {
            super(itemView);
            specname=itemView.findViewById(R.id.spec_txt);
            img=itemView.findViewById(R.id.spec_img);
            container=itemView.findViewById(R.id.spec_container);

        }
    }

}
