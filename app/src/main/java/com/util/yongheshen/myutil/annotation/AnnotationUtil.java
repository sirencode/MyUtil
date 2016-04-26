package com.util.yongheshen.myutil.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Diablo on 16/4/26.
 */
public class AnnotationUtil {

    public static AnnotationUtil anno = null;

    public static AnnotationUtil getInstance() {
        if (anno == null) {
            anno = new AnnotationUtil();
        }
        return anno;
    }

    /**
     * 读取方法上面的注解值
     *
     * @param annotationClasss 处理Annotation类名称
     * @param annotationField  处理Annotation类属性名称
     * @param className        处理Annotation的使用类名称
     * @param methodName       添加注解的方法名
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public Map<String, String> loadMethodVlaue(Class annotationClasss,
                                               String annotationField, String className, String methodName) throws Exception {
        System.out.println("处理Annotation类名称  === " + annotationClasss.getName());
        System.out.println("处理Annotation类属性名称  === " + annotationField);
        System.out.println("处理Annotation的调用类名称  === " + className);
        Map<String, String> map = new HashMap<String, String>();
        Method[] methods = Class.forName(className).getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.isAnnotationPresent(annotationClasss) && method.getName().equals(methodName)) {
                Annotation p = method.getAnnotation(annotationClasss);
                Method m = p.getClass()
                        .getDeclaredMethod(annotationField, null);
                String value = (String) m.invoke(p, null);
                if (!value.equals("")) {
                    System.out.println(m.getName() + "注解值 === " + value);
                    map.put(value, value);
                }
            }
        }
        System.out.println("map数量  === " + map.size());
        return map;
    }

    /**
     * 读取属性上面的注解值
     *
     * @param annotationClasss 处理Annotation类名称
     * @param annotationField  处理Annotation类属性名称
     * @param className        处理Annotation的使用类名称
     * @param attributeName    添加注解的属性名称
     * @return
     * @throws Exception
     */
    public Map<String, String> loadAttributeVlaue(Class annotationClasss,
                                                  String annotationField, String className, String attributeName) throws Exception {
        System.out.println("处理Annotation类名称  === " + annotationClasss.getName());
        System.out.println("处理Annotation类属性名称  === " + annotationField);
        System.out.println("处理Annotation的调用类名称  === " + className);
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = Class.forName(className).getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.isAnnotationPresent(annotationClasss) && field.getName().equals(attributeName)) {
                Annotation p = field.getAnnotation(annotationClasss);
                Method m = p.getClass()
                        .getDeclaredMethod(annotationField, null);
                String value = (String) m.invoke(p, null);
                if (!value.equals("")) {
                    System.out.println(m.getName() + "注解值 === " + value);
                    map.put(value, value);
                }
            }
        }
        System.out.println("map数量  === " + map.size());
        return map;
    }
}
