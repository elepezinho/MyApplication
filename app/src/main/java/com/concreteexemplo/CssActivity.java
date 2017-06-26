package com.concreteexemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.concreteexemplo.adapter.Adapter;
import com.concreteexemplo.model.RepoResponse;
import com.concreteexemplo.model.Repos;
import com.concreteexemplo.rest.ApiClient;
import com.concreteexemplo.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CssActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = CssActivity.class.getSimpleName();
    private final String QUERYL = "language:Css";
    private final String SORTING = "stars";
    private final int CURPAGE = 1;
    private EndlessScrollListener scrollListener;
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    ListView listView;
    List<Repos> repos;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Veja outras líinguagens no menu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Call<RepoResponse> call = apiService.getTopRepos(QUERYL, SORTING, CURPAGE);
        call.enqueue(new Callback<RepoResponse>()
        {

            @Override
            public void onResponse(Call<RepoResponse>call, Response<RepoResponse> response)
            {
                int statusCode = response.code();
                repos = response.body().getItems();
                adapter = new Adapter(getApplicationContext(), R.layout.list_item_repo, repos);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RepoResponse>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        scrollListener = new EndlessScrollListener(5)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                loadMoreData(page);
            }
        };
        listView.setOnScrollListener(scrollListener);
    }

    public void loadMoreData(int page)
    {

        Call<RepoResponse> call = apiService.getTopRepos(QUERYL, SORTING, page);
        call.enqueue(new Callback<RepoResponse>()
        {

            @Override
            public void onResponse(Call<RepoResponse>call, Response<RepoResponse> response)
            {
                int statusCode = response.code();
                adapter.addAll(response.body().getItems());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RepoResponse>call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_11:
                startActivity(new Intent(CssActivity.this,CssActivity.class));
                finish();
                break;
            case R.id.nav_01:
                startActivity(new Intent(CssActivity.this,ArduinoActivity.class));
                finish();
                break;
            case R.id.nav_02:
                startActivity(new Intent(CssActivity.this,CMaisMaisActivity.class));
                finish();
                break;
            case R.id.nav_03:
                startActivity(new Intent(CssActivity.this,CssActivity.class));
                finish();
                break;
            case R.id.nav_04:
                startActivity(new Intent(CssActivity.this,HtmlActivity.class));
                finish();
                break;
            case R.id.nav_05:
                startActivity(new Intent(CssActivity.this,JavaActivity.class));
                finish();
                break;
            case R.id.nav_06:
                startActivity(new Intent(CssActivity.this,JavaScriptActivity.class));
                finish();
                break;
            case R.id.nav_07:
                startActivity(new Intent(CssActivity.this,PhpActivity.class));
                finish();
                break;
            case R.id.nav_08:
                startActivity(new Intent(CssActivity.this,PythonActivity.class));
                finish();
                break;
            case R.id.nav_09:
                startActivity(new Intent(CssActivity.this,RubyActivity.class));
                finish();
                break;
            case R.id.nav_10:
                startActivity(new Intent(CssActivity.this,SwiftActivity.class));
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
