package com.example.fit.entities;

public class GrantAccess {
    private Account account_id;
    private Role role_id;
    private IsGrant isGrant;
    private String note;

    public GrantAccess(Account account_id, Role role_id, IsGrant isGrant, String note) {
        this.account_id = account_id;
        this.role_id = role_id;
        this.isGrant = isGrant;
        this.note = note;
    }

    public GrantAccess(Account account, Role role, IsGrant isGrant) {
        this.account_id = account;
        this.role_id = role;
        this.isGrant = isGrant;
    }
    public GrantAccess() {
    }


    public Account getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Account account_id) {
        this.account_id = account_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public void setIsGrant(IsGrant isGrant) {
        this.isGrant = isGrant;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Role getRole_id() {
        return role_id;
    }

    public IsGrant getIsGrant() {
        return isGrant;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account_id='" + account_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
