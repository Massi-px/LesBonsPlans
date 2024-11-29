package com.coding.app.dispacher;

import com.coding.app.utils.SiteEnum;
import com.coding.app.wrappers.LeBonCoinWrapper;
import com.coding.app.wrappers.SBPWrapper;
import com.coding.app.wrappers.SiteWrapper;
import javafx.collections.ObservableList;

public class Dispatcher {

    private final WrapperManager<SiteWrapper> manager;

    public Dispatcher() {
        this.manager = new WrapperManager<>();
        manager.addWrapper( SiteEnum.LES_BONS_PLANS, new SBPWrapper( SiteEnum.LES_BONS_PLANS ) );
        manager.addWrapper( SiteEnum.LE_BON_COIN, new LeBonCoinWrapper( SiteEnum.LE_BON_COIN ) );
    }


    public <T> void dispatch(SiteEnum site, String keywords, ObservableList<T> obs) {
        var siteWrapper = manager.getWrapper( site );
        siteWrapper.setParams( keywords );
        siteWrapper.search( obs );
    }


}
