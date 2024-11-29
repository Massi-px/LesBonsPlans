package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
        fillObservableList( results, obs );
    }

    private <T> void fillObservableList(String response, ObservableList<T> obs) {

        try {
            var mappingTab = new ObjectMapper().readValue(response, ArrayList.class);

            for (Object o : mappingTab) {
                Map<String, String> map = (Map<String, String>) o;
                Annonce a = new Annonce();
                a.setTitle( map.get( "title" ) );
                a.setPath( map.get( "path" ) );
                //a.setImage( map.get( "image" ) );
                a.setSite( map.get( "link" ) );
                a.setCreatedAt( new Timestamp(System.currentTimeMillis()) );

                displayListings(a, (ObservableList<Annonce>) obs );
            }
            Thread.currentThread().interrupt();
        } catch (JsonProcessingException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException( e );
        }

    }

    private List<Annonce> mapToAnnonces(List<Map<String, String>> results) {

        List<Annonce> list = new ArrayList<>();

        for (Map<String, String> res : results) {
            Annonce a = new Annonce();
            a.setTitle( res.get( "title" ) );
            a.setPath( res.get( "link" ) );
            a.setCreatedAt( new Timestamp(System.currentTimeMillis()) );
            list.add( a );
        }

        return list;

    }
}
