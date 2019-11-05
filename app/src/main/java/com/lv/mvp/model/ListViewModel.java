package com.lv.mvp.model;

import java.io.Serializable;

/**
 * 作者：created by albert on 2019-07-19 14:37
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ListViewModel implements Serializable{

    private String title;
    private String contract;
    private String NewsTitle;
    private String NewsContent;

    public ListViewModel() {
    }

    public ListViewModel(String title, String contract) {
        this.title = title;
        this.contract = contract;
    }


    public ListViewModel(String title, String contract, String newsTitle, String newsContent) {
        this.title = title;
        this.contract = contract;
        NewsTitle = newsTitle;
        NewsContent = newsContent;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }
}
