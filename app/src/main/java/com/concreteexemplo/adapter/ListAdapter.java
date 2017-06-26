package com.concreteexemplo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.concreteexemplo.R;
import com.concreteexemplo.model.Pulls;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Silas Reis on 23/06/17.
 */


public class ListAdapter extends ArrayAdapter<Pulls>
{
    private int rowLayout;
    private List<Pulls> pulls;
    private Context context;
    String pullHtml;
    String pullUrl;
    private static final String TAG = ListAdapter.class.getName();

    public ListAdapter(Context context, int rowLayout, List<Pulls> pulls)
    {
        super(context, rowLayout, pulls);
        this.pulls = pulls;
        this.rowLayout = rowLayout;
        this.context = context;
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
        final PullsViewHolder holder;
        final Pulls pullss = pulls.get(position);
        if (view == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.pulls_items, parent, false);
            holder = new ListAdapter.PullsViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            convertView = view;
            holder = (PullsViewHolder) convertView.getTag();
        }

        pullUrl = pullss.getUserInfo().getAvatarUrl();
        Picasso.with(context).cancelRequest(holder.pullAuthor);
        Picasso.with(context).load(pullUrl).into(holder.pullAuthor);
        holder.pullTitleTextView.setText(pulls.get(position).getPullTitle());
        holder.bodyTextView.setText(pulls.get(position).getPullBody());
        holder.pullAuthorName.setText(pullss.getUserInfo().getLogin());
        holder.pullCreatedAt.setText(convertDate(pulls.get(position).getCreatedAt()));
        pullHtml = pulls.get(position).getPullUrl();


        holder.cardViewPull.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullHtml));
                context.startActivity(browserIntent);
            }
        });

        return convertView;

    }

    public class PullsViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout pullLayout;
        TextView pullTitleTextView;
        TextView bodyTextView;
        TextView pullAuthorName;
        TextView pullCreatedAt;
        ImageView pullAuthor;
        CardView cardViewPull;

        public PullsViewHolder(View v)
        {
            super(v);
            pullLayout = (LinearLayout) v.findViewById(R.id.pullLayout);
            pullAuthor = (ImageView) v.findViewById(R.id.pullAuthor);
            pullTitleTextView = (TextView) v.findViewById(R.id.pullTitleTextView);
            bodyTextView = (TextView) v.findViewById(R.id.bodyTextView);
            pullAuthorName = (TextView) v.findViewById(R.id.pullAuthorName);
            pullCreatedAt = (TextView) v.findViewById(R.id.pullCreatedAt);
            cardViewPull = (CardView) v.findViewById(R.id.cardViewPull);
        }


    }

    public static String convertDate(String dataOriginal)
    {
        String dataConvertida = "";
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date data = sdf.parse(dataOriginal);
            dataConvertida = fmt.format(data);
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Erro ao converter a data", ex);
        }
        return dataConvertida;
    }
}
