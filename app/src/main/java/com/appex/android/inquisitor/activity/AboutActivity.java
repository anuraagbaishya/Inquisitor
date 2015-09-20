package com.appex.android.inquisitor.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appex.android.inquisitor.R;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Infinity.ttf");
        TextView text1 = (TextView) findViewById(R.id.textView);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        TextView text4 = (TextView) findViewById(R.id.textView4);
        TextView text5 = (TextView) findViewById(R.id.textView5);
        TextView htext = (TextView) findViewById(R.id.hyperlink);
        TextView htext2 = (TextView) findViewById(R.id.hyperlink2);
        TextView font = (TextView) findViewById(R.id.fontcourtesy);
        text1.setTypeface(typeface);
        text2.setTypeface(typeface);
        text3.setTypeface(typeface);
        text4.setTypeface(typeface);
        text5.setTypeface(typeface);
        htext.setText(
                Html.fromHtml(
                        "<a href=\"https://github.com/anuraagbaishya/Inquisitor\">See source on GitHub</a> ")
        );
        htext.setMovementMethod(LinkMovementMethod.getInstance());
        htext.setTypeface(typeface);
        htext2.append("Logo Icon by ");
        htext2.append(
                Html.fromHtml(
                        "<a href=\"http://www.freepik.com\">Freepik</a>") //<a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a></div>
        );
        htext2.append(" from ");
        htext2.append(
                Html.fromHtml(
                        "<a href=\"http://www.flaticon.com\">Flaticon</a>")
        );
        htext2.setMovementMethod(LinkMovementMethod.getInstance());
        htext2.append(". Flaticon is licensed under ");
        htext2.append(
                Html.fromHtml(
                        "<a href=\"http://creativecommons.org/licenses/by/3.0/\">CC BY 3.0</a>"
                )
        );
        htext.setMovementMethod(LinkMovementMethod.getInstance());
        htext2.setTypeface(typeface);
        font.append("Fonts used: ");
        font.append(
                Html.fromHtml(
                        "<a href=\"https://www.behance.net/gallery/Infinity/1126535\">Infinity</a>"
                )
        );
        font.setMovementMethod(LinkMovementMethod.getInstance());
        font.append(" and ");
        font.append(
                Html.fromHtml(
                        "<a href=\"http://fontfabric.com/kankin-free-font/\">Kankin</a>"
                )
        );
        font.setMovementMethod(LinkMovementMethod.getInstance());
        font.setTypeface(typeface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
