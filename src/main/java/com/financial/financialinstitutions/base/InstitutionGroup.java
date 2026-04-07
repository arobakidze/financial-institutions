package com.financial.financialinstitutions.base;

public abstract class InstitutionGroup {

    private String groupName;

    public InstitutionGroup(String groupName) {
        this.groupName = groupName;
    }

    public abstract String getGroupType();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}