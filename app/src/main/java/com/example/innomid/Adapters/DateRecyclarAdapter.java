package com.example.innomid.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Activities.ConfirmBookingActivity;
import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Fragments.FragmentConfirmBokking;
import com.example.innomid.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateRecyclarAdapter extends RecyclerView.Adapter<DateRecyclarAdapter.ViewHolder> {


    private Context context;
    private List<Date> dates = null;
    private DateAdapterInterface adapterInterface;
    public boolean isClickable = true;

    public DateRecyclarAdapter(Context context, List<Date> dates, DateAdapterInterface adapterInterface) {

        this.context = context;
        this.dates = dates;
        this.adapterInterface = adapterInterface;
    }


    public DateRecyclarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DateRecyclarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.time_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DateRecyclarAdapter.ViewHolder holder, final int position) {

        final DateRecyclarAdapter adapter = new DateRecyclarAdapter(context);
        adapter.isClickable = true;
        int interval = 30;
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        Calendar time = Calendar.getInstance();
//        time.add(Calendar.MINUTE, interval);
//        String string = String.valueOf(df.format(time.getTime()));
//        holder.txt.setText(SelectDateAndTimeActivity.DATE_FORMAT.format(dates.get(position)));

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
        Date date1 = null;
        try {
            date1 = format.parse("08:00:12 pm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = format.parse("05:30:12 pm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = date1.getTime() - date2.getTime();
        Log.v("Data1", ""+date1.getTime());
        Log.v("Data2", ""+date2.getTime());
        int hours = (int) (mills/(1000 * 60 * 60));
        int mins = (int) (mills/(1000*60)) % 60;

        String diff = hours + ":" + mins+" pm"; // updated value every1 second
        holder.txt.setText(diff);

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
              // holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.tapcolor));

                holder.frameLayout.setBackground(context.getResources().getDrawable(R.drawable.datafillcorners));
                holder.txt.setTextColor(context.getResources().getColor(R.color.dialogtxt));
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setCanceledOnTouchOutside(false);
                final View view = LayoutInflater.from(context).inflate(R.layout.confirm_date, null);
                Button button1 = view.findViewById(R.id.confirm_date);
                alertDialog.setView(view);
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams wm = window.getAttributes();
                wm.gravity = Gravity.BOTTOM;
                wm.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wm);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.frameLayout.setBackground(context.getResources().getDrawable(R.drawable.datatransparentcorners));
                        holder.txt.setTextColor(context.getResources().getColor(R.color.text_invisible));
                        holder.frameLayout.setClickable(false);
                        alertDialog.dismiss();
                        adapter.isClickable = false;
                        ((AppCompatActivity)context).finish();
                        Intent intent=new Intent(context, ConfirmBookingActivity.class);
                        context.startActivity(intent);







                    }
                });

            }
        });


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


    @Override
    public int getItemCount() {
        return dates.size();
    }

    public interface DateAdapterInterface {

        void OnclicDate(int position);

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt;
        FrameLayout frameLayout;

        ViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.time_title);
            frameLayout = itemView.findViewById(R.id.txt_container);
            frameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clicposition = getAdapterPosition();
            adapterInterface.OnclicDate(clicposition);

        }
    }
}
