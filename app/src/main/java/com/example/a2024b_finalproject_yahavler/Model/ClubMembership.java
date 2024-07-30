package com.example.a2024b_finalproject_yahavler.Model;

import java.util.Date;

public class ClubMembership {
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

}

