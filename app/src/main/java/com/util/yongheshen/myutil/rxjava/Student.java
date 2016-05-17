package com.util.yongheshen.myutil.rxjava;

/**
 * Created by Diablo on 16/5/17.
 */
public class Student {
    private String mName;
    private String[] mCourses;

    public Student(String name, String[] courses) {
        mName = name;
        mCourses = courses;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String[] getCourses() {
        return mCourses;
    }

    public void setCourses(String[] courses) {
        mCourses = courses;
    }
}
