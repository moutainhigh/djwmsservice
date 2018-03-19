package com.djcps.wms.commons.fluentvalidator;


import org.springframework.util.ObjectUtils;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.djcps.wms.commons.msg.MsgInterface;


/**
 * @title:长度检验器
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class ValidateNotNullInteger extends ValidatorHandler<Integer> implements Validator<Integer> {
	
		
		/**
		 * 最小值 
		 */
		private Integer mixStringLength;
		
		/**
		 * 最大值 
		 */
		private Integer maxStringLength;
		
		/**
		 * 错误信息
		 */
		private MsgInterface error;
		
		public ValidateNotNullInteger(MsgInterface error){
			this.maxStringLength = Integer.MAX_VALUE;
			this.mixStringLength = 1;
			this.error = error;
		}
		
		public ValidateNotNullInteger(MsgInterface error,Integer maxStringLength){
			this.maxStringLength = maxStringLength;
			this.mixStringLength = 1;
			this.error = error;
		}
		
		public ValidateNotNullInteger(MsgInterface error,Integer mixStringLength,Integer maxStringLength){
			this.maxStringLength = maxStringLength;
			this.mixStringLength = mixStringLength;
			this.error = error;
		}
		
		/**
		 * 校验方法
		 */
		@Override
		public boolean validate(ValidatorContext context, Integer str) {
			if(ObjectUtils.isEmpty(str)){
				context.addErrorMsg(error.getMsg());
				return false;
			}
			if(str< mixStringLength || str > maxStringLength){
				context.addErrorMsg(error.getMsg());
				return false;
			}
			return true;
		}
}
