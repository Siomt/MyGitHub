package com.zhouchatian.mygithub;

/**
 * Created by Mr.Robot on 2017/5/3.
 * https://github.com/TheSadFrog
 */

public class TrendingBean {
    private String title;//标题
    private String synopsis;//简单描述
    private String programmingLanguage;//编程语言
    private String totalStar;//总星星数
    private String todayStar;//今天获得星星数
    private String url;//详情地址

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getTotalStar() {
        return totalStar;
    }

    public void setTotalStar(String totalStar) {
        this.totalStar = totalStar;
    }

    public String getTodayStar() {
        return todayStar;
    }

    public void setTodayStar(String todayStar) {
        this.todayStar = todayStar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TrendingBean{" +
                "title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", totlalStar='" + totalStar + '\'' +
                ", todayStar='" + todayStar + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
