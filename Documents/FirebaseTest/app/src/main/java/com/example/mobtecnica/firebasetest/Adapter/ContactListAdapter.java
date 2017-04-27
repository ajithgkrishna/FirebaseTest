package com.example.mobtecnica.firebasetest.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobtecnica.firebasetest.Model.ContactList;
import com.example.mobtecnica.firebasetest.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mobtecnica on 4/25/2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MainViewHolder> {

    FragmentActivity activity;
    ArrayList<ContactList> contactLists;
    private Context mContext;

    private ContactListClickListener contactClickListener;

    public ContactListAdapter(FragmentActivity activity, ArrayList<ContactList> contactLists, Context mContext) {
        this.activity = activity;
        this.contactLists = contactLists;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(ContactListAdapter.ContactListClickListener contactClickListener) {
        this.contactClickListener = contactClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
        return new MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bindData(position, holder);
    }

    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    public void upDateContactList(ArrayList<ContactList> contactLists) {
        this.contactLists = contactLists;
        notifyDataSetChanged();
    }

    public interface ContactListClickListener {
        public void onClicked(int position, View v);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView thumb;
        TextView name;
        TextView phnNum;

        public MainViewHolder(View itemView) {
            super(itemView);
            thumb = (CircleImageView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.txtname);
            phnNum = (TextView) itemView.findViewById(R.id.txtphoneNo);
            itemView.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        public void bindData(int position, MainViewHolder holder) {

            holder.phnNum.setText(contactLists.get(position).getPhone());
            holder.name.setText(contactLists.get(position).getName());
            try {
                Glide.with(mContext).load(contactLists.get(position).getThumb()).into(holder.thumb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK!");
            contactClickListener.onClicked(getAdapterPosition(), v);
            ;
        }
    }
}
