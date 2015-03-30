package com.appex.android.inquisitor;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.bgbar);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setElevation(0f);
        getSupportActionBar().setBackgroundDrawable(drawable);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_about, container, false);
            Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/1942.ttf");
            TextView text1=(TextView)rootView.findViewById(R.id.textView);
            TextView text2=(TextView)rootView.findViewById(R.id.textView2);
            TextView text3=(TextView)rootView.findViewById(R.id.textView3);
            TextView text4=(TextView)rootView.findViewById(R.id.textView4);
            TextView text5=(TextView)rootView.findViewById(R.id.textView5);
            TextView htext=(TextView)rootView.findViewById(R.id.hyperlink);
            TextView htext2=(TextView)rootView.findViewById(R.id.hyperlink2);
            TextView font=(TextView)rootView.findViewById(R.id.fontcourtesy);
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
            htext2.append(". Flaticon is licensed under ");
            htext2.append(
                    Html.fromHtml(
                            "<a href=\"http://creativecommons.org/licenses/by/3.0/\">CC BY 3.0</a>"
                    )
            );
            htext2.setTypeface(typeface);
            font.setTypeface(typeface);
            return rootView;
        }
    }
}
