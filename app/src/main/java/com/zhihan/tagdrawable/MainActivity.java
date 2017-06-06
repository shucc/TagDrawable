package com.zhihan.tagdrawable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlReporter;

    private RelativeLayout rlReporterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlReporter = (RelativeLayout) findViewById(R.id.rl_reporter);
        rlReporterTwo = (RelativeLayout) findViewById(R.id.rl_reporter_two);

        float ondDP = getResources().getDimension(R.dimen.oneDP);

        TagDrawable tagDrawable1 = new TagDrawable(TagDrawable.ARROW_LEFT, ondDP * 8, ondDP * 8, ondDP * 3, ondDP, ondDP * 12, Color.WHITE, Color.parseColor("#d6d6d6"));
        rlReporter.setBackgroundDrawable(tagDrawable1);

        TagDrawable tagDrawable2 = new TagDrawable(TagDrawable.ARROW_RIGHT, ondDP * 8, ondDP * 8, ondDP * 5, ondDP * 12, Color.parseColor("#e91838"));
        rlReporterTwo.setBackgroundDrawable(tagDrawable2);
    }
}
