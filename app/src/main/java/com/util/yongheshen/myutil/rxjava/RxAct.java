package com.util.yongheshen.myutil.rxjava;

import android.app.Activity;
import android.os.Bundle;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Diablo on 16/5/16.
 * 链式顺序执行的一组异步操作
 */
public class RxAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myObservable.subscribe(mySubscriber);
        mapRx();
        schedulerRx();
        flatMapRx();
    }

    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, RxJava!");
                    sub.onCompleted();
                }
            }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) { System.out.println(s); }

        @Override
        public void onCompleted() { }

        @Override
        public void onError(Throwable e) { }
    };

    /**
     * 可以看到，map() 方法将参数中的 Integer 对象转换成一个 String 对象后返回，而在经过 map() 方法后，
     * 事件的参数类型也由 Integer 转为了 String
     */
    private void mapRx(){
        Observable.just(1) // 输入类型 int
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer filePath) { // 参数类型 String
                        return "转化为："+filePath; // 返回类型 Bitmap
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String info) { // 参数类型 Bitmap
                        System.out.println(info);;
                    }
                });
    }

    /**
     * 在RxJava 中，Scheduler ——调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。RxJava 已经内置了几个 Scheduler ，它们已经适合大多数的使用场景：
     Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
     另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
     */
    private void schedulerRx(){
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        System.out.println("number:" + number);
                    }
                });

//        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .map(mapOperator) // 新线程，由 observeOn() 指定
//                .observeOn(Schedulers.io())
//                .map(mapOperator2) // IO 线程，由 observeOn() 指定
//                .observeOn(AndroidSchedulers.mainThread)
//                .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
    }

    /**
     *  flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map() 不同的是，
     *  flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。
     *  flatMap() 的原理是这样的：1. 使用传入的事件对象创建一个 Observable 对象；2. 并不发送这个 Observable, 而是将它激活，
     *  于是它开始发送事件；3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，
     *  而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。这三个步骤，把事件拆成了两级，
     *  通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
     */
    private void flatMapRx(){
        String[] courses1 = {"语文","数学"};
        Student student1 = new Student("小王",courses1);
        String[] courses2 = {"英语","地理"};
        Student student2 = new Student("小王",courses2);
        Student[] students = {student1,student2};

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String course) {
                System.out.println(course);
            }

        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);
    }
}
