# Android 组件封装

#### Android 组件封装
##### 包含内容
1. MVP封装
2. 网络封装
3. 通用的Dialog封装
4. 扫一扫封装
5. 常用的工具

#### 概述：
项目demo和项目依赖的文件都会上传到Github上，同时本人也会不断的更新和优化里面的内容。
详细的更新情况，我会在公众号中通知大家；也会在GitHub的Readme中更新；如果遇到什么问题。也欢迎各位指出。我只是个小学生，不断的学习中，希望可以和你一起发现其中的问题，如果有兴趣，也可以跟我一起来维护这个小项目，发掘更多更好玩的东西给大家。
（所有的项目结构中都增加了今日头条的适配方案，也解决了各位适配时候的问题 今日头条适配方案的文章介绍：https://www.jianshu.com/p/4aa23d69d481 ）
下面分别对应的每个模块的项目概述和使用；

![项目目录结构](https://github.com/softwareboy92/android_libs/blob/master/images/directory.png)

* * *
### 一. MVP 
##### 简介：
1. 项目中包含BaseView、BasePresenter、BaseFragment、BaseActivity、BaseAdapter(ListView的Adapter、RecycleView的Adapter)
2. 使用方法
a. Contract
``` java
  public interface MvpContract {
    interface View extends BaseContract.BaseView{}
    interface Presenter extends BaseContract.BasePresenter<View>{}
    }
```
b. Presenter
``` java
 public class MvpPresenter extends BasePresenter<MvpContract.View> implements MvpContract.Presenter{
      }
```
c. Activity
``` java
    public class MvpActivity extends BaseActivity<MvpPresenter> implements MvpContract.View {

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected MvpPresenter initPresenter() {
        return new MvpPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }
}
```
  