package com.example.addetect.addetect;
import com.example.addetect.addetect.AdDetect;

import android.app.Application;
import android.app.ProgressDialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.example.addetect.addetect.view_process;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class kjcreations1 extends AppCompatActivity {
    //public Context context=getApplicationContext();

    //private PackageManager packageManager = null;
    //private List<ApplicationInfo> applist = null;


    //private AppAdapter listadapter = null;
    public static ArrayList<String>adslist=new ArrayList<String>();
    String TAG="hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_apps_permission);
        //packageManager = getPackageManager();

        new scrapeLinks().execute();



    }


public class scrapeLinks extends AsyncTask<Void,Void,Void> {
    ProgressDialog pDialog ;
    SQLiteDatabase linkdatabase = openOrCreateDatabase("Links",MODE_PRIVATE,null);


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(kjcreations1.this);
        pDialog.setMessage("Getting ads list ");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }


    @Override
    protected Void doInBackground(Void... params) {
        Document doc = null;
        int temp=0;

        String plink= URI.create("http://www.appbrain.com/stats/libraries/ad").toASCIIString();

        try {
            doc = Jsoup.connect(plink).header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .maxBodySize(0)
                    .timeout(60000).get();

            Elements link = doc.select("a[href]");
            String stringLink = null;

            for (int i = 0; i < link.size(); i++) {

                //link.eq(i);
               stringLink = link.eq(i).toString();
                Document doc1=Jsoup.parse(stringLink);
                String linkname=doc1.body().text();
                System.out.println("testing**************" + linkname);
                if(linkname.isEmpty())
                {

                }else
                {
                    if(linkname.matches("Apps"))
                    {
                        temp=1;

                    }
                    else if(temp==1 && !(linkname.matches("Hot Apps"))) {
                        linkdatabase.execSQL("CREATE TABLE IF NOT EXISTS Adslink(Links VARCHAR(512));");
                        // linkdatabase.execSQL("INSERT INTO Adslink VALUES('linkname');");
                        //System.out.println(stringLink);
                        //SQLiteDatabase db = this.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        adslist.add(linkname);
                        values.put("Links", linkname);
                        linkdatabase.insert("Adslink", null, values);
                    }
                    else if(linkname.matches("Hot Apps"))
                    {
                        break;
                    }
                    else
                    {

                    }
                }


            }
            linkdatabase.close();

            //System.out.println(link);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Element links = doc.select("a[href]").first();
       // System.out.println(links);

        return null;
    }

    protected void onPostExecute(Void result) {
        pDialog.dismiss();
        ArrayAdapter adapter = new ArrayAdapter<String>(kjcreations1.this, R.layout.activity_listview,adslist);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        super.onPostExecute(result);
    }
}
}