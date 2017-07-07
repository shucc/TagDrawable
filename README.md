# TagDrawable

带箭头的drawable

## Gradle

在项目的build.gradle中,添加:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在使用库的module中添加,为避免重复引用,推荐使用exclude::
```groovy
dependencies {
    compile 'com.android.support:support-annotations:latest.version'
    compile ('com.github.shucc:TagDrawable:v1.0') {
        exclude group: 'com.android.support', module: 'support-annotations'
    }
}
```

## 如何使用？

```java
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
```

## Demo
![](https://github.com/shucc/TagDrawable/blob/master/demo/demo.jpg)