package com.example.guo.widgets.text.autolink;

public interface LinkClickListener {
    /**
     * @param url
     * @return true  表示要自己处理  false 使用系统默认
     */
    boolean onLinkClick(String url);
}
