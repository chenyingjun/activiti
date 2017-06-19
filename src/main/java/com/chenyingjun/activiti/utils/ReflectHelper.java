package com.chenyingjun.activiti.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
public class ReflectHelper {
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}
	
	public  static <T> void setFieldValue (T object, final String fieldName, final Object value){
		
		String setMethodName = "set" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1);
		
		String getMethodName = "get" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1);
		
		Class<? extends Object> clazz = object.getClass();
		
		Class<? extends Object> paramType = null;
		
		Object actValue = value;
		
		try {
			
			Method getMethod = clazz.getMethod(getMethodName);
			paramType = getMethod.getReturnType();
			
			System.out.println("fieldNam:"+ fieldName + ".class=" + paramType);
			
			Method setMethod = clazz.getMethod(setMethodName, paramType);
			
			if(paramType == java.util.Date.class){
				actValue = DateUtil.parse2Date(String.valueOf(value));
			}else if(paramType == Double.class){
				actValue = new Double(String.valueOf(value));
			}else if(paramType == Integer.class){
				actValue = Integer.parseInt(String.valueOf(value));
			}else{
				actValue = String.valueOf(value);
			}
			
			setMethod.invoke(object, actValue);
		} catch (Exception e) {
			//
		}
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
                                           Object value) throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	/**
	 * 获取泛型的类型信息
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class getSuperClassGenricType(final Class clazz, final int index){
		Type gentype = clazz.getGenericSuperclass();
		if(!(gentype instanceof ParameterizedType)){
			return Object.class;
		}
		Type[] params = ((ParameterizedType)gentype).getActualTypeArguments();
		if(params == null || params.length<index || index<0){
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 获取泛型的类型信息
	 * @param  
	 * @return Class<T> 
	 */
	public static Class getSuperClassGenricType(final Class clazz){
		return getSuperClassGenricType(clazz,0);
	}
}
