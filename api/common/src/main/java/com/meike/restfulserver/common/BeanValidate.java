package com.meike.restfulserver.common;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 请求参数校验 使用hibernate框架校验入参的有效性，参数可以被@notnull,@length,@min,@max等注解标注
 * 
 * @author lyf
 *
 */
public class BeanValidate {

	/**
	 * 校验对象是否合法
	 * 
	 * @param obj
	 *            对象
	 * @return 校验结果串
	 */
	public static String validateModel(Object obj) { // 验证某一个对象
		if (null == obj) {
			return "";
		}

		StringBuffer buffer = new StringBuffer(64); // 用于存储验证后的错误信息

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);// 验证某个对象,，其实也可以只验证其中的某一个属性的

		Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
		while (iter.hasNext()) {
			String message = iter.next().getMessage();
			buffer.append(message);
			buffer.append(",");
		}
		return buffer.toString();
	}
}
