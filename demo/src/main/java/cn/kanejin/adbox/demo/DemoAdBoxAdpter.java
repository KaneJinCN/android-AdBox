package cn.kanejin.adbox.demo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.kanejin.adbox.AdBoxAdapter;
import cn.kanejin.adbox.AdItem;

/**
 * Created by Kane on 8/4/16.
 */
public class DemoAdBoxAdpter extends AdBoxAdapter {

    public DemoAdBoxAdpter(Context context) {
        super(context);

        fillAdItems();
    }

    private void fillAdItems() {
        List<DemoAdItem> items = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            DemoAdItem item = new DemoAdItem();

            item.setId((long) i);
            item.setTitle("Demo Ad " + i);
            item.setUrl("http://domain.com/ad/link/" + i);
            item.setImageUrl("http://wowslider.com/images/data/images/slide" + (i + 1) + ".png");

            items.add(item);

        }

        setItems(items);
    }
}
