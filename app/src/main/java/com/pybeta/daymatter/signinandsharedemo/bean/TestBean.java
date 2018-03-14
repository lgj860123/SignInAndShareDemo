package com.pybeta.daymatter.signinandsharedemo.bean;

/**
 * 测试类对象
 * Created by luogj on 2018/2/8.
 */

public class TestBean {
    /*id	int	广告id
    advId	string	广告id,这个参数 用于广告统计接口
    positionId	int	广告位置id
    form	int	广告形式 1文字+图片 2纯文字 3纯图片
    actType	int	广告类型1：H5， 2：app下载 3：应用内（原生跳转）
    title	string	标题
    image	string	图片
    url	string	url地址
    identification	int	标识ID 例如actType=3 值1001则跳转原生的主题皮肤
    loginRequired	int	跳转是否需要登录 1需要0不需要*/

    private int id;
    private String advId;
    private int positionId;
    private int form;
    private int actType;
    private String title;
    private String image;
    private String url;
    private int identification;
    private int loginRequired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getActType() {
        return actType;
    }

    public void setActType(int actType) {
        this.actType = actType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public int getLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(int loginRequired) {
        this.loginRequired = loginRequired;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", advId='" + advId + '\'' +
                ", positionId=" + positionId +
                ", form=" + form +
                ", actType=" + actType +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", identification=" + identification +
                ", loginRequired=" + loginRequired +
                '}';
    }
}
