package com.hrsoftbd.rz.restaurentsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url_generate")
    @Expose
    private String urlGenerate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("item_type_id")
    @Expose
    private String itemTypeId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("delivary_policy_id")
    @Expose
    private String delivaryPolicyId;
    @SerializedName("term_policy_id")
    @Expose
    private String termPolicyId;
    @SerializedName("insert_by")
    @Expose
    private String insertBy;
    @SerializedName("insert_time")
    @Expose
    private String insertTime;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlGenerate() {
        return urlGenerate;
    }

    public void setUrlGenerate(String urlGenerate) {
        this.urlGenerate = urlGenerate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDelivaryPolicyId() {
        return delivaryPolicyId;
    }

    public void setDelivaryPolicyId(String delivaryPolicyId) {
        this.delivaryPolicyId = delivaryPolicyId;
    }

    public String getTermPolicyId() {
        return termPolicyId;
    }

    public void setTermPolicyId(String termPolicyId) {
        this.termPolicyId = termPolicyId;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
