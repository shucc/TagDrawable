package com.zhihan.tagdrawable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import org.cchao.tagdrawablelibrary.TagDrawable;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlReporter;

    private RelativeLayout rlReporterTwo;

    private RelativeLayout rlReporterThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlReporter = (RelativeLayout) findViewById(R.id.rl_reporter);
        rlReporterTwo = (RelativeLayout) findViewById(R.id.rl_reporter_two);
        rlReporterThree = (RelativeLayout) findViewById(R.id.rl_reporter_three);

        float oneDP = getResources().getDimension(R.dimen.oneDP);

        TagDrawable tagDrawable1 = new TagDrawable.Builder()
                .setArrowDirection(TagDrawable.ARROW_LEFT)  //设置箭头方向
                .setArrowWidth(oneDP * 8)   //箭头宽度
                .setArrowHeight(oneDP * 8)  //箭头高度
                .setRadiusSize(oneDP * 4)   //边缘圆弧半径
                .setMargin(oneDP * 12)      //箭头向左向右为上边距，箭头向上向下则为左边距
                .setBackgroundColor(Color.WHITE)    //背景色
                .setStrokeColor(Color.parseColor("#d6d6d6"))    //边线颜色
                .setStrokeWidth(oneDP)      //边线宽度
                .build();
        rlReporter.setBackgroundDrawable(tagDrawable1);

        TagDrawable tagDrawable2 = new TagDrawable.Builder()
                .setArrowDirection(TagDrawable.ARROW_RIGHT)
                .setArrowWidth(oneDP * 8)
                .setArrowHeight(oneDP * 8)
                .setRadiusSize(oneDP * 4)
                .setMargin(oneDP * 12)
                .setBackgroundColor(Color.parseColor("#e91838"))
                .build();
        rlReporterTwo.setBackgroundDrawable(tagDrawable2);

        TagDrawable tagDrawable3 = new TagDrawable.Builder()
                .setRadiusSize(oneDP * 5)
                .setBackgroundColor(Color.BLACK)
                .build();
        rlReporterThree.setBackgroundDrawable(tagDrawable3);
    }
}
