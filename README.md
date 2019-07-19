# Android 组件封装

#### Android 组件封装
##### 包含内容
1. MVP封装
2. 网络封装
3. 通用的Dialog封装
4. 扫一扫封装
5. 常用的工具

GitHub：https://github.com/softwareboy92/android_libs

#### 概述：
项目demo和项目依赖的文件都会上传到Github上，同时本人也会不断的更新和优化里面的内容。
详细的更新情况，我会在公众号中通知大家；也会在GitHub的Readme中更新；如果遇到什么问题。也欢迎各位指出。我只是个小学生，不断的学习中，希望可以和你一起发现其中的问题，如果有兴趣，也可以跟我一起来维护这个小项目，发掘更多更好玩的东西给大家。

(所有的项目结构中都增加了今日头条的适配方案，也解决了各位适配时候的问题 今日头条适配方案的文章介绍：https://www.jianshu.com/p/4aa23d69d481 )

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
 public class MvpPresenter extends BasePresenter<MvpContract.View> 
                                implements MvpContract.Presenter{
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
d. ListView Adapter 的使用.创建一个model
``` java
public class ListViewModel {

    private String title;
    private String contract;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }
}

```

开始创建Adapter
``` java
public class ListViewAdapter extends BaseListAdapter<ListViewModel> {
    
    
    public ListViewAdapter(Context context) {
        super(context);
    }

    public ListViewAdapter(Context context, List<ListViewModel> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_01;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_content = view.findViewById(R.id.tv_content);
        
        ListViewModel model = getList().get(position);
        
        tv_title.setText(model.getTitle());
        tv_content.setText(model.getContract());
    }
}
```

有关RecycleView的Adapter创建
``` java
public class RecycleViewAdapter extends BaseSimpleRecycleViewAdapter<ListViewModel> {
    @Override
    protected int bindLayout(int viewType) {
        return R.layout.item_01;
    }

    @Override
    protected void bind(BaseViewHolder holder, ListViewModel data) {
        TextView tv_title = holder.itemView.findViewById(R.id.tv_title);
        TextView tv_content = holder.itemView.findViewById(R.id.tv_content);
        
        tv_title.setText(data.getTitle());
        tv_content.setText(data.getContract());
    }
}
```
有关RecycleView的多布局创建
``` java
public class MultiTypeAdapter extends BaseMultiTypeRecycleAdapter<HotCoinsResponse> 
                                    implements MultiTypeSupport<HotCoinsResponse> {

    private CommonViewHolder.onItemCommonClickListener commonClickListener;

    public MultiTypeAdapter(Context context, List<HotCoinsResponse> dataList) {
        super(context, dataList, R.layout.item_01);
        this.multiTypeSupport = this;
    }
    public MultiTypeAdapter(Context context, List<HotCoinsResponse> dataList, 
            CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, R.layout.item_01);
        this.commonClickListener = commonClickListener;
        this.multiTypeSupport = this;
    }

    @Override
    public int getLayoutId(HotCoinsResponse item, int position) {
        if (item.getSymbol().equals("HT")) {
            return R.layout.item_01;
        }
        return R.layout.item_02;
    }

    @Override
    protected void bindData(CommonViewHolder holder, HotCoinsResponse data) {
        holder.setText(R.id.tv_title, data.getCategory())
                .setText(R.id.tv_content,data.getTokenAddress())
                .setCommonClickListener(commonClickListener);
    }
}

```
* * *
### 二. Retrifit2.0 
##### 简介：
1. 这里简单举例，然后完成使用
 新建一个ApiService的类:
 
 ``` java
public interface ApiService {
   
    @GET("/app/version/")
    Observable<UpdateResponse> getAppVersion();
}
```
UpdateResponse 这个类，需要继承封装中的BaseResponse 这里简单说明下，BaseResponse类中的status 由于项目中的差异，
这个字段返回的值可以跟后台的同事商量下，或者你也可以在本地新建一个BaseResponse 也可以完成相关操作；
``` java
public class UpdateResponse extends BaseResponse<UpdateResponse> {

    private long createTime;
    private String appVersion;
    private String linkUrl;
    private int forceUpdate;
    private int outmoded;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public int getOutmoded() {
        return outmoded;
    }

    public void setOutmoded(int outmoded) {
        this.outmoded = outmoded;
    }
}
```
新建的一个全部的Loader，所有的网络请求都写在此处
``` java
public class Loader extends ObjectLoader implements ApiService {

    private ApiService mApiService;
    private static Loader mLoader;
    //TODO url 为我们要访问的url，这个可以设置全局变量，也可以设置本地变量，这个看你自己心情，这里简单举例了
    private static String url = "";
    public static Loader getLoader() {
        if (mLoader==null){
            mLoader = new Loader();
        }
        return mLoader;
    }
    public Loader() {
        if (mApiService == null)
            mApiService = RetrofitFactory.getFactory(url).createService(ApiService.class);
    }

    public Observable<UpdateResponse> getAppVersion() {
        return mApiService.getAppVersion()
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidScheduler.mainThread());
    }
}
```