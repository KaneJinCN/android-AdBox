package cn.kanejin.adbox;

/**
 * Created by Kane on 8/3/16.
 */
public interface AdBoxListener {
    void onAdShowStart(AdBox adBox, int position, AdItem ad);

    void onAdShowEnd(AdBox adBox, int position, AdItem ad);

    void onAdClick(AdBox adBox, int position, AdItem ad);
}
