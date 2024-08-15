package com.example.a2024b_finalproject_yahavler.DataManagers;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.Store;

import java.util.ArrayList;
import java.util.Arrays;

public class ClubManager {
    public static ArrayList<Club>getClub(){
        ArrayList<Club> clubs = new ArrayList<>();
        clubs=createClubs();
        for (Club club : clubs) {
            club.setAcceptedStores(getStoresForClub(club.getName()));
           // getStoresForClub(club.getClubId());
        }
        return clubs;
    }
    public static ArrayList<Club> createClubs() {
        ArrayList<Club> clubs = new ArrayList<>();
        clubs.add(new Club()
                .setClubId()
                .setName("EXTRA MEMBERS")
                .setLogoResId("https://www.extramembers.co.il/wp-content/uploads/2021/06/logo.png")
                .setDescription("עורכי דין, שופטים, רואי חשבון, יועצי מס, מתמחים ובני/ות זוגם")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון טוב")
                .setLogoResId("https://www.tov.org.il/pics/tov_site/content_logo_yellow_trans_new_lg03.png")
                .setDescription("עובדי מדינה")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("מועדון נכון")
                .setLogoResId("https://www.moadon-nachon.co.il/files/%D7%A0%D7%9B%D7%95%D7%9F%20%D7%9C%D7%95%D7%92%D7%95.png")
                .setDescription("עובדי וגמלאי שירות המדינה, לקוחות בנק יהב")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון יותר")
                .setLogoResId("https://www.yoter.co.il/wp-content/uploads/2023/06/b39c913b374fd11a3d60b76b05e16bde.b197b0.webp")
                .setDescription("חיילים בשירות חובה, מלש\"בים עם צו גיוס עליו מוטבע תאריך גיוס סופי")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון שלך")
                .setLogoResId("https://is1-ssl.mzstatic.com/image/thumb/Purple221/v4/83/a9/c3/83a9c3e7-ebbf-0b98-37a0-3eac96d64a9e/AppIcon-0-0-1x_U007emarketing-0-6-0-85-220.png/1200x630wa.png")
                .setDescription("עובדי הרשויות המקומיות בישראל, התאגידים העירונים וגופי הסמך")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון עדיף")
                .setLogoResId("https://uniq-club-shop.s3.eu-west-1.amazonaws.com/images/a3fb50c5ca27951ba9c31295be8bea89.png")
                .setDescription("נכים ובעלי מוגבלויות")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון תמורה")
                .setLogoResId("https://sherut-il.com/wp-content/uploads/2024/01/%D7%90%D7%A8%D7%92%D7%95%D7%9F-%D7%94%D7%9E%D7%95%D7%A8%D7%99%D7%9D-%D7%A9%D7%99%D7%A8%D7%95%D7%AA-%D7%9C%D7%A7%D7%95%D7%97%D7%95%D7%AA-%D7%9C%D7%95%D7%92%D7%95-768x768.jpg")
                .setDescription("חברי ארגון המורים")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון UNIQ")
                .setLogoResId("https://uniq-club-shop.s3.eu-west-1.amazonaws.com/images/ae757dce2950d7a4827f907cb0be0d84.png")
                .setDescription("סטודנטים, חברי סגל ובוגרי אוניברסיטאות")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון istudent")
                .setLogoResId("https://istudent.co.il/wp-content/uploads/2022/06/%D7%90%D7%99%D7%A1%D7%98%D7%95%D7%93%D7%A0%D7%98-1.png")
                .setDescription("סטודנטים")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון ALL CLUB")
                .setLogoResId("https://www.allclub.co.il/wp-content/uploads/2023/03/all-alub-new-logo-84-30.png")
                .setDescription("עצמאים, עצמאיות, בעלי ובעלות עסקים ועובדיהם")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("מועדון שווה")
                .setLogoResId("https://image.marketing.shavve.co.il/lib/fe3011737364047e721172/m/1/fda1c0b0-4631-4e74-89d1-790bd8134efb.png")
                .setDescription("חברי ההסתדרות הלאומית")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("הייטק זון")
                .setLogoResId("https://media.htzone.co.il/design/header/logo_htz2.png")
                .setDescription("עובדי חברות הייטק שבהסדר עם המועדון")
        );

        clubs.add(new Club()
                .setClubId()
                .setName("צרכנות הוֹט")
                .setLogoResId("https://cdn.hot.co.il/react/static/media/hot-new.e055dc8c257cbd79ff005c4751f2f784.svg")
                .setDescription("הנדסאים, טכנאים, חברי הסתדרות האקדמאים במח\"ר, חברי הסתדרות / לשכת המהנדסים וחברי איגוד העובדים הסוציאליים")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("קרנות השוטרים")
                .setLogoResId("https://play-lh.googleusercontent.com/-zxzYRDVrWbBTik8B7q7kQdL0_d6aJ_X91fxeOBvDRytx6YwAXvM4Wq3Xohb2q1-9w")
                .setDescription("אנשי משטרת ישראל, שוטרים בגמלאות, אנשי המשמר האזרחי ועובדי שירות בתי הסוהר")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("בהצדעה")
                .setLogoResId("https://www.behatsdaa.org.il/static/media/beatsdaha-new-logo.066a9ce6.png")
                .setDescription("משרתי מילואים פעילים, חיילים משוחררים בכבוד (עד 3 שנים לאחר השחרור)")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("חבר")
                .setLogoResId("https://www.hvr.co.il/acmplt.asmx/logo?t=963322150470")
                .setDescription("משרתי קבע וגמלאי צה\"ל וכוחות הבטחון, חברי ארגון נכי צה\"ל ובני/ות זוגם")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("יחד")
                .setLogoResId("https://lp.landing-page.mobi/uploads/users/10833/15415189975be1b695f24f8.jpg")
                .setDescription("רופאים/ות, רוקחים, פסיכולוגים, עובדי חברות כגון חברת החשמל, אל-על, טבע, בנק לאומי ולאומי קארד")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("אשמורת")
                .setLogoResId("https://digital.isracard.co.il/globalassets/cardsimages/131_800_-1/a_ashmoret_gold.png")
                .setDescription("חברי הסתדרות המורים, בני/ות זוגם וסטודנטים להוראה")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("ביחד בשבילך")
                .setLogoResId("data:image/png;base64,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")
                .setDescription("חברי ההסתדרות החדשה")
        );

        return clubs;
    }
    public static ArrayList<String> getStoresForClub(String clubName) {
        ArrayList<String> storesAcceptingClub = new ArrayList<>();
        ArrayList<Store> stores = StoreManager.getStores();

        for (Store store : stores) {
            if (store.getAcceptedClubs().contains(clubName)) {
                storesAcceptingClub.add(store.getName());
            }
        }

        return storesAcceptingClub;
    }
}
