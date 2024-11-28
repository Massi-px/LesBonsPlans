package com.coding.app.dispacher;

import com.coding.app.utils.SiteEnum;
import com.coding.app.wrappers.LeBonCoinWrapper;
import com.coding.app.wrappers.SBPWrapper;
import com.coding.app.wrappers.SiteWrapper;

public class Dispacher {

    private final WrapperManager<SiteWrapper> manager;

    public Dispacher() {
        this.manager = new WrapperManager<>();
        manager.addWrapper( SiteEnum.LES_BONS_PLANS, new SBPWrapper( SiteEnum.LES_BONS_PLANS ) );
        manager.addWrapper( SiteEnum.LE_BON_COIN, new LeBonCoinWrapper( SiteEnum.LE_BON_COIN ) );
    }


    public SiteWrapper dispach(SiteEnum site) {
        return manager.getWrapper( site );
    }


}
