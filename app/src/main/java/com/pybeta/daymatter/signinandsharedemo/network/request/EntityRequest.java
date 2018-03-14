package com.pybeta.daymatter.signinandsharedemo.network.request;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.RequestMethod;

/**
 *
 * Created by luogj on 2018/2/8.
 */

public class EntityRequest<Entity> extends AbstractRequest<Entity> {

    private Class<Entity> aClazz;
    public EntityRequest(String url, RequestMethod requestMethod,Class<Entity> clazz) {
        super(url, requestMethod);
        this.aClazz = clazz;
    }

    @Override
    protected Entity getResult(String responseBody) throws Exception {
        return JSON.parseObject(responseBody, aClazz);
    }
}
