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
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tel Aviv", "Jerusalem")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_aldo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Converse")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Haifa", "Eilat")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_converse.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Fox")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Rishon LeZion", "Ashdod")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_fox.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Foot Locker")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Beer Sheva", "Kfar Saba")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_footlocker_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("GALITA")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Herzliya", "Holon")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/galita_logo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mango")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Petah Tikva", "Bat Yam")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_mango.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lacoste")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ashkelon", "Modiin")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_lacoste.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lee")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Netanya", "Raanana")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_lee.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Nautica")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Hadera", "Carmiel")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_nautica.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("One Project")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tiberias", "Nahariya")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_oneproject_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Jump")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Afula", "Zichron Yaakov")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_jumponot.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Inter Jeans")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tirat Carmel", "Bnei Brak")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_interjeans.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Puma")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Rosh HaAyin", "Kiryat Shmona")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_puma.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Rip Curl")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ramla", "Ramat Hasharon")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ripcurl.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Ruby Ray")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Givatayim", "Or Yehuda")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_rubybay.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Steve Madden")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Kiryat Ata", "Rehovot")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_stevemaddan.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Story")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Hod Hasharon", "Beit Shemesh")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_story.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Skechers")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Beit Shean", "Dimona")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_skechers.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Terminal X")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Yavne", "Kfar Saba")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_terminalx.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Timberland")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ramat Gan", "Sderot")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_timberland_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Under Armour")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Kiryat Yam", "Eilat")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_underarmour.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("We Shoes")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Beer Yaakov", "Rishon LeZion")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_weshoes_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Adidas")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Beer Yaakov", "Rishon LeZion")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_adidas.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("H&O")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Lod", "Kiryat Gat")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ho_2021.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("American Eagle")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ramat Aviv", "Holon")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_americaneagle.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Afrodita")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ashkelon", "Bat Yam")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_afrodita.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Billabong")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tel Aviv", "Jerusalem")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_billabong.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Golf")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Haifa", "Petah Tikva")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_golf.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Gali")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ashdod", "Rehovot")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_gali.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Hugo")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Kfar Saba", "Modiin")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_hugo.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Tommy Hilfiger")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tiberias", "Rishon LeZion")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_tommyhilfiger.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Yanga")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Nahariya", "Raanana")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_yanga.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mashbir")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Givatayim", "Afula")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_mashbir_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Lee Cooper")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Holon", "Kiryat Ata")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_leecooper.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Levi's")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Or Yehuda", "Ramat Hasharon")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_levis.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Michael Kors")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ramla", "Beit Shemesh")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_michaelkors.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Nine West")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Ramat Gan", "Rehovot")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_ninewest.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Polgat")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Sderot", "Dimona")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_polgat.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Mega sport")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Yavne", "Eilat")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_megasport.png")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Factory 54")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Kiryat Gat", "Rosh HaAyin")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_factory54_new.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Columbia")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Kiryat Yam", "Beit Shean")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(false)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_columbia.jpg")
        );

        stores.add(new Store()
                .setStoreId()
                .setName("Calvin Klein")
                .setBranchesLocations(new ArrayList<>(Arrays.asList("Tel Aviv", "Jerusalem")))
                .setAcceptedClubs(new ArrayList<>(Arrays.asList("Hever")))
                .setFavorite(true)
                .setLogo("https://www.mcc.co.il/pics/giftcard/logo_cljeans.jpg")
        );

        return stores;
    }
}
