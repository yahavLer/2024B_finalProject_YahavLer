package com.example.a2024b_finalproject_yahavler.Model;

import java.util.Date;

public class ClubMembership {
    private static  int clubMemberCounter= 0;
    private String CmId;
    private String clubId;
    private String creditCardInfo;
    private Date membershipExpiry;
    private String userId;
    public ClubMembership() {
    }

    // קונסטרקטור
    public ClubMembership(String userId,String clubId, String creditCardInfo, Date membershipExpiry) {
        this.clubId = clubId;
        this.userId = userId;
        this.creditCardInfo = creditCardInfo;
        this.membershipExpiry = membershipExpiry;
    }
    private synchronized String generateId() {
        clubMemberCounter++;
        return "CM" + clubMemberCounter;
    }
    public String getCmId() {
        return CmId;
    }

    public ClubMembership setCmId() {
        this.CmId = generateId();
        return this;
    }

    public Date getMembershipExpiry() {
        return membershipExpiry;
    }

    public ClubMembership setMembershipExpiry(Date membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
        return this;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public ClubMembership setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClubId() {
        return clubId;
    }

    public ClubMembership setClubId(String clubId) {
        this.clubId = clubId;
        return this;
    }

    @Override
    public String toString() {
        return "ClubMembership{" +
                "CmId='" + CmId + '\'' +
                ", clubId='" + clubId + '\'' +
                ", creditCardInfo='" + creditCardInfo + '\'' +
                ", membershipExpiry=" + membershipExpiry +
                ", userId='" + userId + '\'' +
                '}';
    }
}

