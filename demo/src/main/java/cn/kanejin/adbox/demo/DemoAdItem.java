package cn.kanejin.adbox.demo;

import cn.kanejin.adbox.AdItem;

/**
 * Created by Kane on 8/4/16.
 */
public class DemoAdItem implements AdItem {
    private Long id;
    private String imageUrl;
    private String title;
    private String url;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
