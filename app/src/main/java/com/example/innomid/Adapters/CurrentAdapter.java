package com.example.innomid.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Data.CurrentData;
import com.example.innomid.Fragments.FragmentHistory;
import com.example.innomid.Fragments.FrgmentClickCurrentappointmt;
import com.example.innomid.R;
import com.example.innomid.Utils.LaboratoryUtils;

import java.util.List;

public class CurrentAdapter  extends RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>{

    private Context context;
    private List<CurrentData> resultList;
    private SharedPreferences sharedPreferences;

    public CurrentAdapter(Context context, List<CurrentData> resultList) {
        this.context = context;
        this.resultList = resultList;
        sharedPreferences = context.getSharedPreferences(LaboratoryUtils.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public CurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.current_list_item,parent,false);
        return new CurrentViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final CurrentViewHolder holder, final int position) {
        holder.drname.setText(resultList.get(position).getDrName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                holder.linearLayout.setBackground(new ColorDrawable(Color.parseColor("#009cdc")));
                holder.appoint.setTextColor(Color.parseColor("#ffffff"));
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setCanceledOnTouchOutside(false);
                final View view = LayoutInflater.from(context).inflate(R.layout.confirmation_dialog, null);
                RadioGroup radG = (RadioGroup) view.findViewById(R.id.radiog);
                final Button cancel=view.findViewById(R.id.cancelappointment);
                final EditText editText=view.findViewById(R.id.comment);
                radG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected
                        switch(checkedId)
                        {
                            case R.id.rad1:
                                cancel.setEnabled(true);

                                break;

                            case R.id.rad2:

                                cancel.setEnabled(true);
                                break;

                            case R.id.rad3:

                                cancel.setEnabled(true);
                                break;

                            case R.id.rad4:
                                 editText.setEnabled(true);
                                 editText.addTextChangedListener(new TextWatcher() {
                                                                     @Override
                                                                     public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                     }

                                                                     @Override
                                                                     public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                                     }

                                                                     @Override
                                                                     public void afterTextChanged(Editable s) {
                                                                            cancel.setEnabled(true);
                                                                     }
                                                                 });
                                 break;
                        }
                    }
                });
                ImageView imageView=view.findViewById(R.id.imh);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        holder.linearLayout.setBackground(new ColorDrawable(Color.parseColor("#c5eeff")));
                        holder.appoint.setTextColor(Color.parseColor("#009cdc"));
                    }
                });


                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resultList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,resultList.size());
                            alertDialog.dismiss();
                        }
                    });

                alertDialog.setView(view);
                alertDialog.show();
                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams wm = window.getAttributes();
                alertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                wm.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wm);


            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new FrgmentClickCurrentappointmt();
                loadFragment(fragment);

            }
        });


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public class CurrentViewHolder extends RecyclerView.ViewHolder {

        TextView drname;
        TextView specialnamme;
        TextView date;
        TextView time;
        LinearLayout linearLayout;
        TextView appoint;
        CardView cardView;

        public CurrentViewHolder(View itemView) {
            super(itemView);
            drname=itemView.findViewById(R.id.drnc);
            specialnamme=itemView.findViewById(R.id.jobc);
            date=itemView.findViewById(R.id.djt);
            time=itemView.findViewById(R.id.dttxt1);
            linearLayout=itemView.findViewById(R.id.ww1);
            appoint=itemView.findViewById(R.id.appoint);
            cardView=itemView.findViewById(R.id.cardd);


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
