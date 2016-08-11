# Android广告框

## 效果预览


## 使用方法
1. 引用AdBox Library

    在build.gradle中添加依赖
    ```
    compile 'cn.kanejin.adbox:library:1.0.0@aar'
    ```


2. 在layout里定义AdBox
    ```
    <cn.kanejin.adbox.AdBox
        android:id="@+id/ad_box"
        android:layout_height="80dp"
        android:layout_width="match_parent"
        android:layout_alignParentBottom="true"
        custom:placeholder="@drawable/demo_ad_placeholder"
        custom:duration="500"
        custom:delay="3000"
        custom:loop="true"
        custom:auto_play="true" />

    ```
    [查看完整的示例代码](https://github.com/KaneJinCN/android-AdBox/blob/master/demo/src/main/res/layout/activity_main.xml)

3. 在Activity里添加Adapter和Listener
    ```
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

##  参数说明

<table>
<tr>
<th>参数名</th><th>类型</th><th>默认值</th><th>说明</th>
</tr>

<tr>
<td>placeholder</td>
<td>reference</td>
<td>&nbsp;</td>
<td>广告图片加载时的占位图片</td>
</tr>

<tr>
<td>duration</td>
<td>integer</td>
<td>1000</td>
<td>广告图片切换动画时间, 单位ms</td>
</tr>

<tr>
<td>delay</td>
<td>integer</td>
<td>5000</td>
<td>广告停留时间, 单位ms</td>
</tr>

<tr>
<td>loop</td>
<td>boolean</td>
<td>true</td>
<td>广告是否循环播放</td>
</tr>

<tr>
<td>play</td>
<td>boolean</td>
<td>true</td>
<td>是否自动播放</td>
</tr>

</table>
