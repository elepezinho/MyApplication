package com.concreteexemplo.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.concreteexemplo.PullsActivity;
import com.concreteexemplo.R;
import com.concreteexemplo.model.Owner;
import com.concreteexemplo.model.Pulls;
import com.concreteexemplo.model.Repos;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Silas Reis on 23/06/17.
 */

public class Adapter extends ArrayAdapter<Repos>
{
    private int rowLayout;
    private List<Repos> repos;
    private Owner owner;
    private Pulls pulls;
    private Context context;
    String name;
    String ownerLogin;
    String ownerUrl;

    public Adapter(Context context, int rowLayout, List<Repos> repos)
    {
        super(context, rowLayout, repos);
        this.repos = repos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        View convertView;
        final GitViewHolder holder;
        final Repos reposs = repos.get(position);
        if (view == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false);
            holder = new GitViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            convertView = view;
            holder = (GitViewHolder) convertView.getTag();
        }

        ownerUrl = reposs.getOwnerInfo().getAvatarUrl();
        Picasso.with(context).cancelRequest(holder.authorPhoto);
        Picasso.with(context).load(ownerUrl).into(holder.authorPhoto);
        holder.titleTextView.setText(repos.get(position).getName());
        holder.detailsTextView.setText(repos.get(position).getDescription());
        holder.usernameTextView.setText(reposs.getOwnerInfo().getLogin());
        holder.forkNumber.setText(repos.get(position).getForksCount());
        holder.starNumber.setText(repos.get(position).getStarsCount());


        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ownerLogin = repos.get(position).getOwnerInfo().getLogin();
                name = repos.get(position).getName();
                Intent intent = new Intent(context.getApplicationContext(), PullsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("owner", ownerLogin);
                intent.putExtra("name", name);
                context.startActivity(intent);

            }
        });

        return convertView;

    }

    public class GitViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout repoLayout;
        TextView titleTextView;
        TextView detailsTextView;
        TextView usernameTextView;
        TextView forkNumber;
        TextView starNumber;
        ImageView authorPhoto;
        CardView cardView;

        public GitViewHolder(View v)
        {
            super(v);
            repoLayout = (LinearLayout) v.findViewById(R.id.repo_layout);
            authorPhoto = (ImageView) v.findViewById(R.id.authorPhoto);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            detailsTextView = (TextView) v.findViewById(R.id.detailsTextView);
            usernameTextView = (TextView) v.findViewById(R.id.usernameTextView);
            forkNumber = (TextView) v.findViewById(R.id.forkNumber);
            starNumber = (TextView) v.findViewById(R.id.starNumber);
            cardView = (CardView) v.findViewById(R.id.cardView);
        }


    }

}
