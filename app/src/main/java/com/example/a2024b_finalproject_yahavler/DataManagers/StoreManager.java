package com.example.a2024b_finalproject_yahavler.DataManagers;

import com.example.a2024b_finalproject_yahavler.Model.Store;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreManager {
    public static ArrayList<Store> getStores() {
        ArrayList<Store> stores = new ArrayList<>();

        stores.add(new Store()
                .setStoreId()
                .setName("Aldo")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_aldo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Converse")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_converse.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Fox")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_fox.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Foot Locker")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "יותר")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_footlocker_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("GALITA")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון שלך")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/galita_logo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mango")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "אשמורת")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_mango.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lacoste")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_lacoste.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lee")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_lee.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Nautica")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "יותר")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_nautica.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("One Project")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_oneproject_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Jump")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_jumponot.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Inter Jeans")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "יותר")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_interjeans.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Puma")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_puma.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Rip Curl")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ripcurl.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Ruby Ray")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_rubybay.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Steve Madden")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_stevemaddan.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Story")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_story.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Skechers")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_skechers.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Terminal X")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון שלך")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_terminalx.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Timberland")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_timberland_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Under Armour")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_underarmour.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("We Shoes")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_weshoes_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Adidas")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_adidas.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("H&O")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון שלך")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ho_2021.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("American Eagle")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה","istudent" ,"מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_americaneagle.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Afrodita")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_afrodita.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Billabong")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ", "istudent")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_billabong.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Golf")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה" ,"קרנות השוטרים")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_golf.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Gali")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "יחד")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_gali.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Hugo")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "אשמורת")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_hugo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Tommy Hilfiger")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "UNIQ")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_tommyhilfiger.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Yanga")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_yanga.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mashbir")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_mashbir_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lee Cooper")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון שלך")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_leecooper.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Levi's")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_levis.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Michael Kors")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_michaelkors.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Nine West")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ninewest.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Polgat")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_polgat.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mega sport")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "תמורה")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_megasport.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Factory 54")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_factory54_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Columbia")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "ALL CLUB")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_columbia.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Calvin Klein")
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("חבר", "בהצדעה", "מועדון נכון")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_cljeans.jpg")
        );

        return stores;
    }

}
