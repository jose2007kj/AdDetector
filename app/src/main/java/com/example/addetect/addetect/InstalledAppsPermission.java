package com.example.addetect.addetect;
import com.example.addetect.addetect.view_process;

import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

public class InstalledAppsPermission extends AppCompatActivity {

    //private PackageManager packageManager = null;
    //private List<ApplicationInfo> applist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_apps_permission);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, kjcreations1.adslist);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

    }

    //SQLiteDatabase Adlist = openOrCreateDatabase("ScrappedList",MODE_PRIVATE,null);

}