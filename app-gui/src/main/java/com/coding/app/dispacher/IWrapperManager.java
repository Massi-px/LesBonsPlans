package com.coding.app.dispacher;

import com.coding.app.utils.SiteEnum;
import com.coding.app.wrappers.SiteWrapper;

import java.util.List;

public interface IWrapperManager<T> {

    void addWrapper(SiteEnum siteEnum, T wrapper);
    T getWrapper(SiteEnum site);

}
