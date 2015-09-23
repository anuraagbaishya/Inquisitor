package com.appex.android.inquisitor.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.appex.android.inquisitor.R;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Infinity.ttf");
        TextView aboutTextView = (TextView) findViewById(R.id.about_text_view);
        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView developerTextView = (TextView) findViewById(R.id.developer_text_view);
        TextView copyrightTextView = (TextView) findViewById(R.id.copyright_text_view);
        TextView githubLinkTextView = (TextView) findViewById(R.id.github_link_text_view);
        TextView iconCourtesyTextView = (TextView) findViewById(R.id.icon_courtesy_text_view);
        TextView fontCourtesyTextView = (TextView) findViewById(R.id.font_courtesy_text_view);
        aboutTextView.setTypeface(typeface);
        titleTextView.setTypeface(typeface);
        developerTextView.setTypeface(typeface);
        copyrightTextView.setTypeface(typeface);
        githubLinkTextView.setText(
                Html.fromHtml(
                        "<a href=\"https://github.com/anuraagbaishya/Inquisitor\">See source on GitHub</a> ")
        );
        githubLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        githubLinkTextView.setTypeface(typeface);
        iconCourtesyTextView.append("Logo Icon by ");
        iconCourtesyTextView.append(
                Html.fromHtml(
                        "<a href=\"http://www.freepik.com\">Freepik</a>")
        );
        iconCourtesyTextView.append(" from ");
        iconCourtesyTextView.append(
                Html.fromHtml(
                        "<a href=\"http://www.flaticon.com\">Flaticon</a>")
        );
        iconCourtesyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        iconCourtesyTextView.append(". Flaticon is licensed under ");
        iconCourtesyTextView.append(
                Html.fromHtml(
                        "<a href=\"http://creativecommons.org/licenses/by/3.0/\">CC BY 3.0</a>"
                )
        );
        githubLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        iconCourtesyTextView.setTypeface(typeface);
        fontCourtesyTextView.append("Fonts used: ");
        fontCourtesyTextView.append(
                Html.fromHtml(
                        "<a href=\"https://www.behance.net/gallery/Infinity/1126535\">Infinity</a>"
                )
        );
        fontCourtesyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        fontCourtesyTextView.append(" and ");
        fontCourtesyTextView.append(
                Html.fromHtml(
                        "<a href=\"http://fontfabric.com/kankin-free-font/\">Kankin</a>"
                )
        );
        fontCourtesyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        fontCourtesyTextView.setTypeface(typeface);
    }
}
