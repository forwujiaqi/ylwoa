package com.ylwoa.model;

import java.util.Date;

public class ExcelData {
    private Long id;

    private Long excelId;

    private String excelName;

    private Long version;

    private Integer status;

    private Long projectId;

    private String projectName;

    private Integer deleteFlg;

    private Date createTime;

    private Date planStartDate;

    private Date planEndDate;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    private String dataJson;

    private String settingJson;

    private String owner;

    private String ownerName;

    @Override
    public String toString() {
        return "ExcelData{" +
                "id=" + id +
                ", excelId=" + excelId +
                ", excelName='" + excelName + '\'' +
                ", version=" + version +
                ", status=" + status +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", deleteFlg=" + deleteFlg +
                ", createTime=" + createTime +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", dataJson='" + dataJson + '\'' +
                ", settingJson='" + settingJson + '\'' +
                ", owner='" + owner + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getSettingJson() {
        return settingJson;
    }

    public void setSettingJson(String settingJson) {
        this.settingJson = settingJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExcelId() {
        return excelId;
    }

    public void setExcelId(Long excelId) {
        this.excelId = excelId;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName == null ? null : excelName.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }
}