package com.lv.mvp.model;

import com.lv.libhttp.model.BaseResponse;

import java.util.List;

/**
 * 作者：created by albert on 2019-07-18 14:17
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class HotCoinsResponse extends BaseResponse<List<HotCoinsResponse>> {

    private int id;
    private String erc20Precision;
    private String logo;
    private String symbol;
    private String description;
    private long dateDdded;
    private String tokenAddress;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getErc20Precision() {
        return erc20Precision;
    }

    public void setErc20Precision(String erc20Precision) {
        this.erc20Precision = erc20Precision;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDateDdded() {
        return dateDdded;
    }

    public void setDateDdded(long dateDdded) {
        this.dateDdded = dateDdded;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
