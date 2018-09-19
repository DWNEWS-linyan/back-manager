package com.ly.po;

import java.io.Serializable;

public class ToDoAppProjectTaskUser implements Serializable {
    private Integer id;

    private Integer projectTaskId;

    private Integer userInfoId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectTaskId() {
        return projectTaskId;
    }

    public void setProjectTaskId(Integer projectTaskId) {
        this.projectTaskId = projectTaskId;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
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
        ToDoAppProjectTaskUser other = (ToDoAppProjectTaskUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectTaskId() == null ? other.getProjectTaskId() == null : this.getProjectTaskId().equals(other.getProjectTaskId()))
            && (this.getUserInfoId() == null ? other.getUserInfoId() == null : this.getUserInfoId().equals(other.getUserInfoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectTaskId() == null) ? 0 : getProjectTaskId().hashCode());
        result = prime * result + ((getUserInfoId() == null) ? 0 : getUserInfoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectTaskId=").append(projectTaskId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}