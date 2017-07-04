package com.example.addetect.addetect;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android .view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdDetect extends AppCompatActivity {
    TextView textView;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detect);
       // textView = (TextView) findViewById(R.id.textView);

}
    public void click1(View view) {
       // String message="Scanning ....Please Wait";
       // textView.setText(message);
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("List Of Apps")) {
            Intent intent = new Intent(this, Scanning.class);
            startActivity(intent);
        }
        else if(((Button) view).getText().toString().equals("scrap"))
        {
            Intent intent = new Intent(this, kjcreations1.class);
            startActivity(intent);
        }
        else
        {
            if(kjcreations1.adslist.isEmpty())
            {
                CharSequence text = "Ads List is empty !";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(this,text ,duration ).show();
            }
            else
            {
                Intent intent = new Intent(this, InstalledAppsPermission.class);
                startActivity(intent);

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ad_detect, menu);
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
}
