package com.ly.po;

import java.io.Serializable;
import java.util.Date;

public class SysEmail implements Serializable {
    private Integer id;

    private String cc;

    private String to;

    private String bcc;

    private String title;

    private String content;

    private String status;

    private Date createTime;

    private Integer from;

    private String simpleBrokerPrefix;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc == null ? null : bcc.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
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
        SysEmail other = (SysEmail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCc() == null ? other.getCc() == null : this.getCc().equals(other.getCc()))
            && (this.getTo() == null ? other.getTo() == null : this.getTo().equals(other.getTo()))
            && (this.getBcc() == null ? other.getBcc() == null : this.getBcc().equals(other.getBcc()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getFrom() == null ? other.getFrom() == null : this.getFrom().equals(other.getFrom()))
            && (this.getSimpleBrokerPrefix() == null ? other.getSimpleBrokerPrefix() == null : this.getSimpleBrokerPrefix().equals(other.getSimpleBrokerPrefix()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCc() == null) ? 0 : getCc().hashCode());
        result = prime * result + ((getTo() == null) ? 0 : getTo().hashCode());
        result = prime * result + ((getBcc() == null) ? 0 : getBcc().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getFrom() == null) ? 0 : getFrom().hashCode());
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
        sb.append(", cc=").append(cc);
        sb.append(", to=").append(to);
        sb.append(", bcc=").append(bcc);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", from=").append(from);
        sb.append(", simpleBrokerPrefix=").append(simpleBrokerPrefix);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}