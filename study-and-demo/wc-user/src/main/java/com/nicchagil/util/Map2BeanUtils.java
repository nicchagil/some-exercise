package com.nicchagil.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

public class Map2BeanUtils {
	
	/**
	 * Map类型的List装换为指定Class的Bean类型的List
	 */
	public static <T> List<T> mapList2BeanList(Class<T> clazz, List<Map<String, String>> mapList) {
		if (CollectionUtils.isEmpty(mapList)) {
			return Lists.newArrayList();
		}
		
		List<T> beanList = Lists.newArrayList();
		
		try {
			for (Map<String, String> map : mapList) {
				T t = clazz.newInstance();
				BeanUtils.populate(t, map);
				
				beanList.add(t);
			}
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new RuntimeException(e);
		}
		
		return beanList;
	}
	
	/**
	 * Map类型的List装换为指定Class的Bean类型的List
	 */
	public static <T> List<T> mapList2BeanListByConverters(Class<T> clazz, List<Map<String, String>> mapList, ConvertUtilsBean convertUtilsBean) {
		if (CollectionUtils.isEmpty(mapList)) {
			return Lists.newArrayList();
		}
		
		List<T> beanList = Lists.newArrayList();
		BeanUtilsBean beanUtilsBean = null;
		if (convertUtilsBean != null) {
			beanUtilsBean = new BeanUtilsBean(convertUtilsBean);
		} else {
			beanUtilsBean = new BeanUtilsBean();
		}
		
		try {
			for (Map<String, String> map : mapList) {
				T t = clazz.newInstance();
				beanUtilsBean.populate(t, map);
				
				beanList.add(t);
			}
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new RuntimeException(e);
		}
		
		return beanList;
	}

}
