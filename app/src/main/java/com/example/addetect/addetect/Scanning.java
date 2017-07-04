package com.example.addetect.addetect;
import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import com.example.addetect.addetect.kjcreations1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import dalvik.system.DexFile;

public class Scanning extends ListActivity {
    public Context context;

    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;


    private AppAdapter listadapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click1_);
        packageManager = getPackageManager();
        new LoadApplications().execute();
    }
   /**    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //Intent intent1 = new Intent(this,InstalledAppsPermission.class);
        //startActivity(intent1);
        ApplicationInfo app = applist.get(position);
           Intent intent = new Intent(this, view_process.class);
           Bundle extras = new Bundle();
           extras.putString("Pkg", app.packageName);
           extras.putString("Source", app.sourceDir);
           extras.putInt("Id",app.uid);
           extras.putCharSequence("Name",app.loadLabel(packageManager));
           intent.putExtras(extras);
           startActivity(intent);

          // setContentView(R.layout.activity_view_process);
           //kjcreations(app, pm);


           try{

           /**Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);
             if(intent != null)
             {
                              // startActivity(intent);
             }
        } catch(ActivityNotFoundException e) {
            Toast.makeText(Scanning.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Toast.makeText(Scanning.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
**/

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {

        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        for(ApplicationInfo info : list) {
            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appList.add(info);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return appList;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadapter = new AppAdapter(Scanning.this, R.layout.activity_click1_, applist);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Scanning.this, null, "Loading apps info...");
            super.onPreExecute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_click1_, menu);
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

    //=============


    //==============

}
