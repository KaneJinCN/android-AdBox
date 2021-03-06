# Android广告框

## Preview 效果预览

![AdBox Preview Gif](https://cloud.githubusercontent.com/assets/7828293/17585743/f9aca824-5fef-11e6-97e2-16f38a21632b.gif)


## Usage 使用方法
1. 引用AdBox Library

    在build.gradle中添加依赖
    ```gradle
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'cn.kanejin.adbox:library:1.0.4@aar'
    ```

2. 在layout里定义AdBox
    ```xml
    <cn.kanejin.adbox.AdBox
        android:id="@+id/ad_box"
        android:layout_height="80dp"
        android:layout_width="match_parent"
        android:layout_alignParentBottom="true"
        custom:adbox_placeholder="@drawable/demo_ad_placeholder"
        custom:adbox_duration="500"
        custom:adbox_delay="3000"
        custom:adbox_loop="true"
        custom:adbox_autoPlay="true" />

    ```
    [查看完整的示例代码](https://github.com/KaneJinCN/android-AdBox/blob/master/demo/src/main/res/layout/activity_main.xml)

3. 在Activity里设置Adapter和Listener
    ```java
    mAdBox = (AdBox) findViewById(R.id.ad_box);

    DemoAdBoxAdpter adapter = new DemoAdBoxAdpter(this);

    mAdBox.setAdapter(adapter);

    mAdBox.setListener(new AdBoxListener() {
        @Override
        public void onAdShowStart(AdBox adBox, int position, AdItem ad) {

        }

        @Override
        public void onAdShowEnd(AdBox adBox, int position, AdItem ad) {

        }

        @Override
        public void onAdClick(AdBox adBox, int position, AdItem ad) {
            Toast.makeText(MainActivity.this, ad.getTitle(), Toast.LENGTH_SHORT).show();
        }
    });

    ```

    [查看完整的示例代码](https://github.com/KaneJinCN/android-AdBox/blob/master/demo/src/main/java/cn/kanejin/adbox/demo/MainActivity.java)

## Attributes 参数说明

<table style="width:100%;">
<tr>
<th>参数名</th><th>类型</th><th>默认值</th><th>说明</th>
</tr>

<tr>
<td>adbox_placeholder</td>
<td>reference</td>
<td>&nbsp;</td>
<td>广告加载时的占位图片</td>
</tr>

<tr>
<td>adbox_duration</td>
<td>integer</td>
<td>1000</td>
<td>广告切换动画时间, 单位ms</td>
</tr>

<tr>
<td>adbox_delay</td>
<td>integer</td>
<td>5000</td>
<td>广告停留时间, 单位ms</td>
</tr>

<tr>
<td>adbox_loop</td>
<td>boolean</td>
<td>true</td>
<td>是否循环播放</td>
</tr>

<tr>
<td>adbox_autoPlay</td>
<td>boolean</td>
<td>true</td>
<td>是否自动播放</td>
</tr>

</table>

## License 许可
[MIT](https://github.com/KaneJinCN/android-AdBox/blob/master/LICENSE)

## See Also 参阅
[TextSlider](https://github.com/KaneJinCN/android-TextSlider)