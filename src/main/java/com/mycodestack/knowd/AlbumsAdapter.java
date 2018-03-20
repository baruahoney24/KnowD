package com.mycodestack.knowd;

/**
 * Created by lakshay on 20/3/18.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
import java.util.Random;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<DoctorData> albumList;
    public Typeface myfonts ;
    static int f , g , ans ;
    public TextView HospitalCardText2 , DoctorCardText2 , WaitingTimeText2 , DistanceCardText2 , CurrentNumberCardText2 ;

    String color[] = {"#7cfc00" , "#7fff00" ,"#228b22" ,"#006400"};
    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView HospitalCardText , DoctorCardText , WaitingTimeText , DistanceCardText , CurrentNumberCardText ;
        public MyViewHolder(View view) {
            super(view);
            HospitalCardText =(TextView) view.findViewById(R.id.hospitalCardText);
            WaitingTimeText = (TextView)view.findViewById(R.id.WaitingTimeCardText);
            DoctorCardText = (TextView)view.findViewById(R.id.DoctorCardText);
            DistanceCardText = (TextView)view.findViewById(R.id.DistanceCardText);
            CurrentNumberCardText = (TextView)view.findViewById(R.id.CurrentNumberCardText);


            HospitalCardText2 =(TextView) view.findViewById(R.id.hospitalCardText);
            WaitingTimeText2 = (TextView)view.findViewById(R.id.WaitingTimeCardText);
            DoctorCardText2 = (TextView)view.findViewById(R.id.DoctorCardText);
            DistanceCardText2 = (TextView)view.findViewById(R.id.DistanceCardText);
            CurrentNumberCardText2 = (TextView)view.findViewById(R.id.CurrentNumberCardText);

        }
    }


    public AlbumsAdapter(Context mContext, List<DoctorData> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_type,null);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        DoctorData album = albumList.get(position);
        Random r = new Random();
        String a = f + " k.m. away";
        String b = "Waiting Time " + g+" hrs";
        holder.DistanceCardText.setText(a);

        String sd = album.getCurrent_number();
        if(sd == null){
            sd = "0";
        }
        holder.CurrentNumberCardText.setText(String.valueOf(sd));
        holder.DoctorCardText.setText(album.getDoctorName());
        holder.HospitalCardText.setText(album.getHospitalName());
        holder.WaitingTimeText.setText(b);
        HospitalCardText2.setBackgroundColor(Color.parseColor(color[position%4]));



        // loading album cover using Glide library

    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }
}