package com.lv.mvp.model;


import com.lv.libhttp.model.BaseResponse;

/**
 * 作者：created by albert on 2019-07-18 13:49
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class UpdateResponse extends BaseResponse<UpdateResponse> {

    private long createTime;
    private String appVersion;
    private String linkUrl;
    private int forceUpdate;
    private int outmoded;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public int getOutmoded() {
        return outmoded;
    }

    public void setOutmoded(int outmoded) {
        this.outmoded = outmoded;
    }
}
