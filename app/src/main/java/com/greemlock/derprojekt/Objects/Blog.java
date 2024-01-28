package com.greemlock.derprojekt.Objects;

public class Blog {

    String blogKey;
    String blogTitle;
    String blogSubtitle;
    String blogText;

    public Blog(String blogKey, String blogTitle, String blogSubtitle, String blogText) {
        this.blogKey = blogKey;
        this.blogTitle = blogTitle;
        this.blogSubtitle = blogSubtitle;
        this.blogText = blogText;
    }

    public Blog() {
    }

    public String getBlogKey() {
        return blogKey;
    }
    public void setBlogKey(String blogKey) {
        this.blogKey = blogKey;
    }

    public String getBlogTitle() {
        return blogTitle;
    }
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSubtitle() {
        return blogSubtitle;
    }
    public void setBlogSubtitle(String blogSubtitle) {
        this.blogSubtitle = blogSubtitle;
    }

    public String getBlogText() {
        return blogText;
    }
    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }
}
