package com.reagryan.online_banking.dto.request;

public class AuditLogRequest {
    private String userId;
    private String action;
    private MetaDataRequest metaDataRequest;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MetaDataRequest getMetaDataRequest() {
        return metaDataRequest;
    }

    public void setMetaDataRequest(MetaDataRequest metaDataRequest) {
        this.metaDataRequest = metaDataRequest;
    }
}
