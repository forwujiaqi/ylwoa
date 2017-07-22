package com.ylwoa.model;

import java.util.Date;

public class Excel {
    private Long id;

    private Integer excelType;

    private Long excelId;

    private String excelName;

    private Long version;

    private Integer status;

    //TODO
    private Integer deleteFlg;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private String progressRange;

    private Date planStartDate;

    private Date planEndDate;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    private String dataJson;

    private String settingJson;

    public String getProgressRange() {
        return progressRange;
    }

    public void setProgressRange(String progressRange) {
        this.progressRange = progressRange;
    }

    @Override
    public String toString() {
        return "Excel{" +
                "id=" + id +
                ", excelType=" + excelType +
                ", excelId=" + excelId +
                ", excelName='" + excelName + '\'' +
                ", version=" + version +
                ", status=" + status +
                ", deleteFlg=" + deleteFlg +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", dataJson='" + dataJson + '\'' +
                ", settingJson='" + settingJson + '\'' +
                '}';
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
        this.excelName = excelName;
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
        this.createUserName = createUserName;
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
        this.updateUserName = updateUserName;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getExcelType() {
        return excelType;
    }

    public void setExcelType(Integer excelType) {
        this.excelType = excelType;
    }
}