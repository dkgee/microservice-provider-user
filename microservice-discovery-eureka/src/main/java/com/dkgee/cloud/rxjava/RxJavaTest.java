package com.dkgee.cloud.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


/**
 * 描述：RxJava
 *      异步、观察者模式， 发送数据，接收数据
 *
 *      Observable：可观察
 *      Observer:   观察者
 *
 *      Subject: 主题
 *
 *      Subscriber: 订阅者
 *
 *      Subscription: 订阅对象
 *
 *      Action0...n：动作/行为
 *
 *      Func0...n：函数
 *
 *
 * 作者: JinHuaTao
 * 时间：2017/10/20 17:43
 */
public class RxJavaTest {

    public static void test(){
        //数据发射源
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //发送数据
                subscriber.onNext("Hi, dk!");
            }
        });

        //数据接收源
        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onCompleted() {
                //数据接收完成时调用
            }

            @Override
            public void onError(Throwable throwable) {
                //发生错误调用
            }

            @Override
            public void onNext(String s) {
                //正常接收数据调用
                System.out.println(s);
            }
        };

        //关联发射源和接收源
        sender.subscribe(receiver);
    }
    public static void main(String[] args) {
        test();
    }

}
