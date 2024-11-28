package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;

import java.util.List;


public class LeBonCoinWrapper extends SiteWrapper {

    public LeBonCoinWrapper(SiteEnum siteEnum) {
        super( siteEnum.getUrl(), "" );
    }

    public LeBonCoinWrapper(SiteEnum siteEnum, String params) {
        super( siteEnum.getUrl(), params );
    }

    @Override
    public List<Annonce> search() {

        return ApiUtils.getListLeBonCoin( getUrl(), getParams() );
    }


}
