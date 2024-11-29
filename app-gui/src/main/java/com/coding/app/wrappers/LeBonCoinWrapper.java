package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;

import static com.coding.app.utils.ApiUtils.displayListings;


public class LeBonCoinWrapper extends SiteWrapper {

    public LeBonCoinWrapper(SiteEnum siteEnum) {
        super( siteEnum.getUrl(), "" );
    }

    public LeBonCoinWrapper(SiteEnum siteEnum, String params) {
        super( siteEnum.getUrl(), params );
    }

    @Override
    public <T> void search(ObservableList<T> obs) {
        String response = ApiUtils.getListLeBonCoin( getUrl(), getParams());
        fillObservableList( response, obs );
    }

    private <T> void fillObservableList(String response, ObservableList<T> obs) {
        Document doc        = Jsoup.parse(response);
        Elements         adListCard = doc.select("div.mb-lg");
        Elements         adCards    = adListCard.select("div.styles_adCard__JzKik");

        for (Element adCard : adCards) {
            String title = adCard.select("p[data-qa-id=aditem_title]").text();
            String date = adCard.select("p[aria-label^=Date]").text();
            String lienannonce = adCard.select("a[data-qa-id=aditem_container]").attr("href");
            String location = adCard.select("p[aria-label^=Située]").text();
            String imageUrl = adCard.select("img").attr("src");

            Annonce annonce = new Annonce(
                    title,
                    imageUrl,
                    //imageUrl,
                    "https://www.leboncoin.fr"+ lienannonce,
                    new Timestamp(System.currentTimeMillis())

            );
            displayListings(annonce, (ObservableList<Annonce>) obs );
        }
        Thread.currentThread().interrupt();
    }
}

