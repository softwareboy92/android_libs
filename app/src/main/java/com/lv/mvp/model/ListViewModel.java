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

    public ListViewModel() {
    }

    public ListViewModel(String title, String contract) {
        this.title = title;
        this.contract = contract;
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
