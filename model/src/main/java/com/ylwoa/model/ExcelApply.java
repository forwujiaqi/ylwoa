package com.ylwoa.model;

import java.util.Date;

public class ExcelApply {
    private Long id;

    private Long excelId;

    private Date applyTime;

    private Integer applyUserId;

    private Date confirmTime;

    private Integer confirmUserId;

    private Date finalConfirmTime;

    private Integer finalConfirmUserId;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(Integer confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public Date getFinalConfirmTime() {
        return finalConfirmTime;
    }

    public void setFinalConfirmTime(Date finalConfirmTime) {
        this.finalConfirmTime = finalConfirmTime;
    }

    public Integer getFinalConfirmUserId() {
        return finalConfirmUserId;
    }

    public void setFinalConfirmUserId(Integer finalConfirmUserId) {
        this.finalConfirmUserId = finalConfirmUserId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}