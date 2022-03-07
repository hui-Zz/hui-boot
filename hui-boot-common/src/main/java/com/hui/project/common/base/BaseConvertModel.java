package com.hui.project.common.base;

import java.io.Serializable;


/**
 * <p>
 * 类转换父类
 * </p>
 *
 * @author hui
 */
public class BaseConvertModel implements Serializable {

	/**
	 * 获取自动转换后的JavaBean对象
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T convert(Class<T> clazz) {
		return BeanConverter.convert(clazz, this);
	}

	public interface Create {
	}

	public interface Update {
	}

	public interface Describe {
	}

	public interface Delete {
	}

	public interface Status {
	}

}
