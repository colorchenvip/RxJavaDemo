
##在高手肩膀上练功之RxSamples
    使用到的技术和三方库：Material Design设计规范、

## 项目简介

* 使用的库

```java
    compile 'com.android.support:appcompat-v7:24.2.1'
     compile 'com.android.support:support-v4:24.2.1'
     compile 'com.android.support:support-v13:24.2.1'
     compile 'com.android.support:design:24.2.1'
     compile 'com.android.support:cardview-v7:24.2.1'
     <!-- retrofit-->
     compile 'com.squareup.okhttp3:okhttp:3.2.0'
     compile 'com.squareup.retrofit2:retrofit:2.0.0'
     compile 'com.squareup.retrofit2:converter-gson:2.0.0'
     compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0'
     compile 'com.github.bumptech.glide:glide:3.7.0'
     compile 'com.jakewharton:butterknife:7.0.1'
     <!--rxJava-->
     compile 'io.reactivex:rxjava:1.1.0'
     compile 'io.reactivex:rxandroid:1.1.0'
     compile 'com.jakewharton.rxbinding:rxbinding:0.4.0'
     compile 'io.reactivex:rxjava:1.0.10'
 ```
 
 

##RxJava 和 Retrofit 结合使用的几个最常见使用方式举例。

1. **基本使用**

  实现最基本的网络请求和结果处理。

2. **转换(map)**

  把返回的数据转换成更方便处理的格式再交给 Observer。

3. **压合(zip)**

  将不同接口并行请求获取到的数据糅合在一起后再处理。

4. **缓存**

  使用 RxJava 中的 BehaviorSubject 缓存数据。

### apk 下载
[直接下载地址：]RxJavaDemo_1.01.apk](https://github.com/colorchenvip/RxJavaDemo/download/RxJavaDemo_1.01.apk)

### 参考
* [gitHub](https://github.com/rengwuxian/RxJavaSamples)