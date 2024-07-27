package com.example.a2024b_finalproject_yahavler.Model;

import java.util.Date;

public class ClubMembership {
    private String clubId;
    private String creditCardInfo;
    private Date membershipExpiry;

    // קונסטרקטור
    public ClubMembership(String clubId, String creditCardInfo, Date membershipExpiry) {
        this.clubId = clubId;
        this.creditCardInfo = creditCardInfo;
        this.membershipExpiry = membershipExpiry;
    }

    public Date getMembershipExpiry() {
        return membershipExpiry;
    }

    public void setMembershipExpiry(Date membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
// גטרים וסטטרים
    // ... (השלמת הקוד בהתאם לצורך)
}

