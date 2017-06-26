package com.concreteexemplo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.concreteexemplo.adapter.ListAdapter;
import com.concreteexemplo.model.Pulls;
import com.concreteexemplo.rest.ApiClient;
import com.concreteexemplo.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullsActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    List<Pulls> pulls;
    ListAdapter adapter;
    ListView pullsListView;
    String name;
    String owner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulls);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        owner = bundle.getString("owner");
        pullsListView = (ListView) findViewById(R.id.pullsListView);

        Call<List<Pulls>> call = apiService.getAllPulls(owner, name);
        call.enqueue(new Callback<List<Pulls>>()
        {

            @Override
            public void onResponse(Call<List<Pulls>>call, Response<List<Pulls>> response)
            {
                int statusCode = response.code();
                pulls = response.body();
                adapter = new ListAdapter(getApplicationContext(), R.layout.pulls_items, pulls);
                pullsListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pulls>>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
