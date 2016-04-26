package com.util.yongheshen.myutil.annotation;

/**
 * Created by Diablo on 16/4/26.
 */
public class Apple {

    @MyAnnotations(name = "Apple")
    public String appleName;

    @MyAnnotations(color = "color")
    public String appleColor;

    @MyAnnotations(name = "Apple")
    public void getName(){
        try {
            AnnotationUtil.getInstance().loadAttributeVlaue(MyAnnotations.class, "name",
                    Apple.class.getName(),"appleName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @MyAnnotations(color = "color")
    public void getFirstName(){

    }

}
