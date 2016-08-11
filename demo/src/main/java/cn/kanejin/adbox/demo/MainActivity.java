package cn.kanejin.adbox.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.kanejin.adbox.AdBox;
import cn.kanejin.adbox.AdBoxListener;
import cn.kanejin.adbox.AdItem;

public class MainActivity extends AppCompatActivity {

    private AdBox mAdBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdBox();

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdBox.showNextAd();

            }
        }, 10000);*/

    }

    private void initAdBox() {
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdBox.play();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mAdBox.pause();
    }
}
