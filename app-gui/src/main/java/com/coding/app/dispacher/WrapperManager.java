package com.coding.app.dispacher;

import com.coding.app.utils.SiteEnum;

import java.util.HashMap;
import java.util.Map;

public class WrapperManager<T> implements IWrapperManager<T> {

    private final Map<SiteEnum,T> wrappers;

    public WrapperManager() {
        this.wrappers = new HashMap<>();
    }

    @Override
    public void addWrapper(SiteEnum siteEnum, T wrapper) {
        this.wrappers.put(siteEnum, wrapper);
    }

    @Override
    public T getWrapper(SiteEnum site) {
        return this.wrappers.get(site);
    }
}
