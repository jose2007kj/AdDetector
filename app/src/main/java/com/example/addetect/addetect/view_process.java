package com.example.addetect.addetect;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dalvik.system.DexFile;
public class view_process extends AppCompatActivity {


    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_process);

        final PackageManager pm = getPackageManager();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String Pkg = extras.getString("Pkg");
        String Name= extras.getString("Name");
        String Source= extras.getString("Source");
        int Id=extras.getInt("Id");
        ArrayList<String>values=kjcreations(pm, Pkg, Name, Source, Id);
        HashSet<String> ilist = new HashSet<>(values);
        String [] entha= ilist.toArray(new String[ilist.size()]);
        List<String> list = Arrays.asList(entha);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, list);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

    }

    public ArrayList<String> kjcreations(PackageManager pm,String Pkg,String Name,String Source,int Id)
    {

        ArrayList<String> mylist = new ArrayList<String>();


        String TAG="myactivity";

        if (Source.startsWith("/data/app/"))
        {
            //Non-system app


            Log.d(TAG, "Package: "+Name);
            Log.d(TAG, "UID: " + Id);
            Log.d(TAG, "Directory: " + Source);




                try {
                    //int temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;

                    DexFile df = new DexFile(Source);
                    for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); )
                    {

                        String s = iter.nextElement();
                       // boolean contains = s.matches(".*\\bsdk\\b.*");
                            for(String adname:kjcreations1.adslist)
                            {
                               // Pattern ptrn=Pattern.compile(adname);

                                String rex = new StringBuilder().append("^*.*").append(adname).append("*.*").toString();
                               // Log.d(TAG,"sheriyanno????"+rex);
                                Pattern rexp=Pattern.compile(rex);
                                Matcher m=rexp.matcher(s);
                                if(m.matches()==true)
                                {
                                    mylist.add(adname);
                                    continue;
                                }
                                    /**
                                    if (s.matches("^*.*Admob*.*") == true) {


                                        if (temp1 == 0) {
                                            mylist.add("Admob");
                                            Log.d(TAG, "Found AdMob ");
                                            temp1 = 1;
                                        }
                                    }
                                    if (s.matches(".*InMobi*.*") == true) {


                                        if (temp2 == 0) {
                                            mylist.add("InMobi");
                                            Log.d(TAG, "Found InMobi");
                                            temp2 = 1;
                                        }

                                    }
                                    if (s.matches(".*AdColony*.*") == true) {


                                        if (temp3 == 0) {
                                            mylist.add("AdColony");
                                            Log.d(TAG, "Found AdColony");
                                            temp3 = 1;
                                        }
                                    }

                                    if (s.matches(".*Chartboost*.*") == true) {


                                        if (temp4 == 0) {
                                            mylist.add("Chartboost");
                                            Log.d(TAG, "Found Chartboost");
                                            temp4 = 1;
                                        }
                                    }
                                    if (s.matches(".*MoPub*.*") == true) {

                                        if (temp5 == 0)

                                        {
                                            mylist.add("MoPub");
                                            Log.d(TAG, "Found MoPub");
                                            temp5 = 1;
                                        }
                                    }
                                    if (s.matches(".*Tapjoy*.*") == true) {
                                        if (temp6 == 0) {
                                            mylist.add("Tapjoy");
                                            Log.d(TAG, "Found Tapjoy ");
                                            temp6 = 1;
                                        }
                                    }**/


                            }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        return (mylist);
    }



}
