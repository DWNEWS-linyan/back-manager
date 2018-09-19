package com.ly.po;

import java.io.Serializable;
import java.util.Date;

public class Notepad implements Serializable {
    private Integer id;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer hour;

    private Integer minute;

    private Integer buildUser;

    private Date createTime;

    private Integer status;

    private String content;

    private String simpleBrokerPrefix;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getBuildUser() {
        return buildUser;
    }

    public void setBuildUser(Integer buildUser) {
        this.buildUser = buildUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSimpleBrokerPrefix() {
        return simpleBrokerPrefix;
    }

    public void setSimpleBrokerPrefix(String simpleBrokerPrefix) {
        this.simpleBrokerPrefix = simpleBrokerPrefix == null ? null : simpleBrokerPrefix.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Notepad other = (Notepad) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getHour() == null ? other.getHour() == null : this.getHour().equals(other.getHour()))
            && (this.getMinute() == null ? other.getMinute() == null : this.getMinute().equals(other.getMinute()))
            && (this.getBuildUser() == null ? other.getBuildUser() == null : this.getBuildUser().equals(other.getBuildUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getSimpleBrokerPrefix() == null ? other.getSimpleBrokerPrefix() == null : this.getSimpleBrokerPrefix().equals(other.getSimpleBrokerPrefix()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getHour() == null) ? 0 : getHour().hashCode());
        result = prime * result + ((getMinute() == null) ? 0 : getMinute().hashCode());
        result = prime * result + ((getBuildUser() == null) ? 0 : getBuildUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getSimpleBrokerPrefix() == null) ? 0 : getSimpleBrokerPrefix().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", hour=").append(hour);
        sb.append(", minute=").append(minute);
        sb.append(", buildUser=").append(buildUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", simpleBrokerPrefix=").append(simpleBrokerPrefix);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}