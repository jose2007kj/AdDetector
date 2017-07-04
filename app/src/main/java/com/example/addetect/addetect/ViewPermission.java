package com.example.addetect.addetect;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by user on 2/28/2016.
 */
public class ViewPermission extends AppCompatActivity {


    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);
      /**  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final PackageManager pm = getPackageManager();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String Pkg = extras.getString("Pkg");
        String Name= extras.getString("Name");
        String Source= extras.getString("Source");
        int Id=extras.getInt("Id");
        ArrayList<String>list=kjcreations(pm, Pkg, Name, Source, Id);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, list);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
**/
        final PackageManager pm = getPackageManager();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String Pkg = extras.getString("Pkg");
        //String Name= extras.getString("Name");
        //String Source= extras.getString("Source");
        int Id=extras.getInt("Id");

        ArrayList<String> list=viewpermission(pm, Pkg);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, list);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

    }
        public ArrayList<String>viewpermission(PackageManager pm,String Pkg)
        {
            ArrayList<String> mylist = new ArrayList<String>();
            StringBuffer permissions = new StringBuffer();


        try {
            PackageInfo packageInfo = pm.getPackageInfo(Pkg, PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            if (requestedPermissions != null) {
                for (int i = 0; i < requestedPermissions.length; i++) {
                    //String name=requestedPermissions[i];
                    //String[] upper=name.split("$*");
                    //Log.d(TAG, "Permissions: " + upper);

                    //(s.matches(".*Admob*") == true)

                    //=====================
                   String rex = new StringBuilder().append("^*.*").append("permission").append(".").toString();

                    String []split=requestedPermissions[i].split(rex);
                   // Log.d(TAG, "Permissions: ************" + split[1]);


                    //==========================
                    //String name=split[1];
                    mylist.add(split[1]);
                    permissions.append(requestedPermissions[i] + "\n");

                }
                Log.d(TAG, "Permissions: " + permissions);
                //mylist.add();
            }
        }
        catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace(); }


        return (mylist);

    }
}