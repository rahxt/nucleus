package com.crossedproductions.nucleus;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.content.Context;
import android.util.Log;
import com.crossedproductions.nucleus.adapter.ListDrugsAdapter;
import com.crossedproductions.nucleus.database.DatabaseHelper;
import com.crossedproductions.nucleus.model.Drugs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ActionBar actionBar;
    TextView textView;
    View view;
    EditText text;
    Button button;

    private ListView lvDrugs;
    private ListDrugsAdapter adapter;
    private List<Drugs> mDrugsList;
    private DatabaseHelper mDBHelper;


// O n            C R E A T E
// O n            C R E A T E
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //toolbar and drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        //  mTitle.setText(toolbar.getTitle());
        //  getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
  //      if (savedInstanceState == null) {
   //         getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
 //                  new FragmentHelp()).commit();
  //          navigationView.setCheckedItem(R.id.nav_help);
  //      }
        //toolbar and drawer
        text= (EditText) findViewById(R.id.idweight);
        button= (Button) findViewById(R.id.idbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weight = Integer.parseInt(text.getText().toString());
                int result = weight;
                Toast.makeText(ListActivity.this, "Input: " + result , Toast.LENGTH_LONG).show();
            }
        });



        lvDrugs = (ListView)findViewById(R.id.listview_drugs);
        mDBHelper = new DatabaseHelper(this);

        //Check database availability
        Log.w("ListActivity","Database availability check");
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

        if(!database.exists()) {
            Log.w("ListActivity","Database does not exist 1");
            mDBHelper.getReadableDatabase();
            Log.w("ListActivity","Database does not exist 2");

            if(copyDatabase(this)) {
                Log.w("ListActivity","Database does not exist 3");
                Log.w("ListActivity","Attempt to copy database");
                Toast.makeText(this, "Database imported successfully", Toast.LENGTH_LONG).show();
                Log.w("ListActivity","Database Copied");
            } else {
                Toast.makeText(this, "Unable to import database", Toast.LENGTH_LONG).show();
                Log.w("ListActivity","Database Copy Error");
                return;
            }
          //  mDBHelper.close();
        }
        Log.w("ListActivity","Database already exists,  copy abort");
        Log.w("ListActivity","Getting Database using DB HELPER");
        mDrugsList = mDBHelper.getListDrugs();
        Log.w("ListActivity","Starting Database Adapter");
        adapter = new ListDrugsAdapter(this, mDrugsList);
        Log.w("ListActivity","Showing data in listview");
        lvDrugs.setAdapter(adapter);
    }

    //  ON CREATE END



    //navigation selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_help:
                Intent i = new Intent(this, FragmentHelp.class);
                Log.w("ListActivity","FragmentHelp Selected");
                startActivity(i);
                break;
            case R.id.nav_settings:
                Intent j = new Intent(this, FragmentSettings.class);
                Log.w("ListActivity","FragmentSettings Selected");
                startActivity(j);
                break;
            case R.id.nav_disclaimer:
                Intent k = new Intent(this, FragmentDisclaimer.class);
                Log.w("ListActivity","FragmentDisclaimer Selected");
                startActivity(k);
                break;
            case R.id.nav_about:
                Intent l = new Intent(this, FragmentAbout.class);
                Log.w("ListActivity","FragmentAbout Selected");
                startActivity(l);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    File file = new File(Environment.getExternalStorageDirectory(), "my.db");

    InputStream  in  = null;
    OutputStream out = null;




    private boolean copyDatabase(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
/*
           DatabaseHelper helper = new DatabaseHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String outFileName = database.getPath();
            database.close();
*/
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("ListActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    //back press sidebar close
    @Override
    public void onBackPressed() { if (drawer.isDrawerOpen(GravityCompat.START)) { drawer.closeDrawer(GravityCompat.START); } else { super.onBackPressed(); }
    }

}



