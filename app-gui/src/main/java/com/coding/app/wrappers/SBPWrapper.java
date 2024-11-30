package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import static com.coding.app.utils.ApiUtils.displayListings;

public class SBPWrapper extends SiteWrapper {

    public SBPWrapper(SiteEnum siteEnum) {
        super( siteEnum.getUrl(), "" );
    }

    public SBPWrapper(SiteEnum siteEnum, String params) {
        super( siteEnum.getUrl(), params );
    }

    @Override
    public <T> void search(ObservableList<T> obs) {
        String results = ApiUtils.jsonResponseAPI( getUrl(), getParams() );
        if (results == null) {
            obs.addFirst( (T) (ApiUtils.LES_BONS_PLANS_UNAVAILABLE) );
            Thread.currentThread().interrupt();
            return;
        }
        fillObservableList( results, obs );
    }

    private <T> void fillObservableList(String response, ObservableList<T> obs) {

        try {
            var mappingTab = new ObjectMapper().readValue(response, ArrayList.class);

            for (Object o : mappingTab) {
                Map<String, Object> map = (Map<String, Object>) o;
                Annonce a = new Annonce();
                a.setTitle( (String) map.get( "title" ) );
                a.setPath( (String) map.get( "link" ) );
                a.setImage( null );
                a.setSite("http://localhost:8080");
                a.setCreatedAt( new Timestamp(System.currentTimeMillis()) );

                displayListings(a, (ObservableList<Annonce>) obs );
            }
            Thread.currentThread().interrupt();
        } catch (JsonProcessingException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException( e );
        }
    }

}
