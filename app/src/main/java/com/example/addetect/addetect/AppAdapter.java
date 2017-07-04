package com.example.addetect.addetect;
        import java.util.List;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        //import static android.support.v4.app.ActivityCompat.startActivity;

public class AppAdapter extends ArrayAdapter<ApplicationInfo>{
    private static final String TAG = "MyActivity";
    private List<ApplicationInfo> appList = null;
    private Context context;
    private PackageManager packageManager;
    public AppAdapter(Context context, int resource,
                      List<ApplicationInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.appList = objects;
        packageManager = context.getPackageManager();
    }
    @Override
    public int getCount()
    {
        return ((null != appList) ? appList.size() : 0);
    }
    @Override
    public ApplicationInfo getItem(int position)
    {
        return ((null != appList) ? appList.get(position) : null);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list, null);
        }
        ApplicationInfo data = appList.get(position);
        if(null != data) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            //TextView packageName = (TextView) view.findViewById(R.id.app_package);
            ImageView iconView = (ImageView) view.findViewById(R.id.img);
            appName.setText(data.loadLabel(packageManager));
            //packageName.setText(data.packageName);
            iconView.setImageDrawable(data.loadIcon(packageManager));
        }


        Button permission = (Button) view.findViewById(R.id.button2);
        permission.setTag(position);
        permission.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                String button_text;

                 button_text = ((Button) v).getText().toString();
                    int position1 =(Integer)v.getTag();
                 if (button_text.equals("Perm"))
                  {

                        Log.d(TAG, "inside permission"+position1);
                      ApplicationInfo app = appList.get(position1);
                      Intent intent = new Intent(getContext(), ViewPermission.class);
                      Bundle extras = new Bundle();
                      extras.putString("Pkg", app.packageName);
                      extras.putString("Source", app.sourceDir);
                      extras.putInt("Id",app.uid);
                      extras.putCharSequence("Name",app.loadLabel(packageManager));
                      intent.putExtras(extras);
                      context.startActivity(intent);



                  }
                }});



        Button ads = (Button) view.findViewById(R.id.button);
        ads.setTag(position);
        ads.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String button_text;

                    button_text = ((Button) v).getText().toString();
                    int position1 =(Integer)v.getTag();
                    if (button_text.equals("Ads"))
                    {

                        Log.d(TAG, "inside Ads"+position1);
                        ApplicationInfo app = appList.get(position1);
                        Intent intent = new Intent(getContext(), view_process.class);
                        Bundle extras = new Bundle();
                        extras.putString("Pkg", app.packageName);
                        extras.putString("Source", app.sourceDir);
                        extras.putInt("Id",app.uid);
                        extras.putCharSequence("Name",app.loadLabel(packageManager));
                        intent.putExtras(extras);
                        context.startActivity(intent);




                    }
                }

                });





        return view;
    }
}