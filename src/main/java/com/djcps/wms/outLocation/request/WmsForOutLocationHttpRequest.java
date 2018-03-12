package com.djcps.wms.outLocation.request;

import com.djcps.wms.commons.config.ParamsConfig;

import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield="WMS_SERVER",urlbean=ParamsConfig.class)
public interface WmsForOutLocationHttpRequest {
	
}
