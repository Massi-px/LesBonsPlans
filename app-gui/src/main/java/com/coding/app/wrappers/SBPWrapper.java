package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SBPWrapper extends SiteWrapper {

    public SBPWrapper(SiteEnum siteEnum) {
        super( siteEnum.getUrl(), "" );
    }

    public SBPWrapper(SiteEnum siteEnum, String params) {
        super( siteEnum.getUrl(), params );
    }

    @Override
    public List<Annonce> search() {
        List<Map<String, String>> results = ApiUtils.jsonResponseAPI( getUrl(), getParams() );

        return mapToAnnonces( results );
    }

    private List<Annonce> mapToAnnonces(List<Map<String, String>> results) {

        List<Annonce> list = new ArrayList<>();

        for (Map<String, String> res : results) {
            Annonce a = new Annonce();
            a.setTitle( res.get( "title" ) );
            a.setPath( res.get( "path" ) );
            a.setImage( res.get( "image" ) );
            a.setSite( res.get( "link" ) );
            a.setCreatedAt( new Timestamp(System.currentTimeMillis()) );
            list.add( a );
        }

        return list;

    }
    /*
        private int id;
    private String title;
    private String path;
    private String image;
    private String site;
    private Timestamp createdAt;
     */

}
