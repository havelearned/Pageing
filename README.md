# Android Jetpack简介

2018年谷歌I/O 发布了一系列辅助android开发者的实用工具，合称Jetpack，以帮助开发者构建出色的 Android 应用。



这次发布的 Android Jetpack 组件覆盖以下 4 个方面：**Architecture（架构）、Foundation（基础）、Behavior（行为） 以及 UI（界面）**。包括我们在本次 Android P Beta 中带来的 Slices（切片）等新功能也包含在其中。此外，**Android Jetpack 完美兼容 Kotlin 语言**，利用 Android KTX 可大幅节省代码量。作为下一代的 Android 组件，Android Jetpack 通过提供现代化应用架构以及提供强健的向后兼容能力等方式，让开发者能够快速、轻松地创造拥有卓越性能的高质量应用。



## 新组件

Android Jetpack 附带五个新组件：

- WorkManager alpha 版

  - **WorkManager**

    WorkMananager（工作管理者） 组件是一个功能强大的新库，可以为基于约束的后台作业（需要有保障的执行）提供一站式解决方案，消除了使用作业或 SyncAdapter（同步适配器） 等框架的需求。WorkManager 提供了一个简化的现代化 API、在安装或未安装 Google Play 服务的设备上运行的功能、创建工作图的功能以及查询工作状态的功能。早期反馈非常令人鼓舞，我们希望确保也能覆盖您的用例。您可以了解我们目前已经完成的工作，并提供对 [WorkManager 组件 ](https://d.android.com/arch/workmanager)alpha 版的反馈。

  ------

  

- 导航 alpha 版

  - **导航**

    尽管 Activity（活动） 是系统提供的您的应用界面的入口点，但在相互分享数据以及转场方面，Activity 表现得不够灵活，这就让它不适合作为构建您的应用内导航的理想架构。今天，我们宣布推出导航组件，作为构建您的应用内界面的框架，重点是让单 Activity 应用成为首选架构。利用导航组件对 Fragment 的原生支持，您可以获得架构组件的所有好处（例如生命周期和 ViewModel），同时让此组件为您处理 FragmentTransaction 的复杂性。此外，导航组件还可以让您声明我们为您处理的转场。它可以自动构建正确的“向上”和“返回”行为，包含对深层链接的完整支持，并提供了帮助程序，用于将导航关联到合适的 UI 小部件，例如抽屉式导航栏和底部导航。但这些并不是全部！ [Android Studio 3.2 ](https://developer.android.com/studio/preview/)中的导航编辑器让您可以直观地查看和管理导航属性：

    [导航组件 ](https://d.android.com/arch/navigation)目前也处于 alpha 版阶段，我们希望收到您的反馈。

    ps:大概就是android底部的导航效果，列如：bilibili底部的选项或者 点击头像是罗列出一个又一个的抽屉式导航效果

    

- 分页稳定版

  - ## **分页**

    应用中呈现的数据可能非常大，这就导致加载的开销比较大，因此，避免一次下载、创建或呈现过多数据就显得非常重要。 [分页组件 ](https://d.android.com/arch/paging)1.0.0 版让您可以轻松加载和呈现大型数据集，同时在您的 RecyclerView 中进行快速、无限滚动。它可以从本地存储和/或网络加载分页数据，并让您能够定义内容的加载方式。此组件原生支持 Room、LiveData 和 RxJava。

    

    

- 切片 alpha 版

  - ## **切片**

    最后，切片组件让一组新功能在 Android Jetpack 中首次亮相。“切片”是一种以搜索结果形式在 Google 智能助理内部显示应用界面的方式：

    可以访问 Android 开发者网站， [详细了解切片组件 ](http://todo/)，以及如何将其集成到您的应用中。

    ------

    

- Android KTX（Kotlin 扩展程序）alpha 版

  - ## **Android KTX**

    最后，Android Jetpack 利用 Kotlin 语言功能的一个目标是提高您的效率。Android KTX可以让您将类似下面所示的 Kotlin 代码：

    

    ```kotlin
    view.viewTreeObserver.addOnPreDrawListener(
      object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
          viewTreeObserver.removeOnPreDrawListener(this)
          actionToBeTriggered()
          return true
        }
    });
    ```

    转换成如下所示的更精简的 Kotlin 代码：

    

    ```kotlin
    view.doOnPreDraw { actionToBeTriggered() }
    ```

    这只是将 Kotlin 支持带到 Android Jetpack 组件的第一步；我们的目标是让 Android Jetpack 造福 Kotlin 开发者（当然还有 Java 开发者）。您可以在 Android 开发者网站上 [详细了解 Android KTX](https://developer.android.com/kotlin/ktx)。

  

  

  **总结：**

  ******Andoird Jetpcack 加入4个大组件 分别是 ：**

  - Architecture（架构）

  - Foundation（基础）

  - Behavior（行为） 

  - 以及 UI（界面）

    

  **jetpack又附带五个组件分别是：**

  - WorkManager alpha
  - 导航 alpha 版
  - 分页稳定版
  - 切片 alpha 版
  - ndroid KTX（Kotlin 扩展程序）alpha 版

## ViewMode 介绍

Android中的ViewModel是一个可以用来存储UI相关的数据的类。ViewModel的生命周期会比创建它的Activity、Fragment的生命周期长。

官方图片：

<img src="image\ViewMode-Lifecycle.png" style="zoom:33%;" />



**这张图是在在没任何设置屏幕发生转换Activity的生命周期变化和ViewModel的生命周期。可以看重建的时候，ViewModel中的数据是不会被清理的。**



ViewMode有两个优点

- Activity进行重建的时候，ViewModel的数据不会被回收调用。这时候我们就可以不用通过onSaveInstanceState()方法来进行数据的存储了。而且用onSaveInstanceState()方法为了使Activity能够尽快的重建还只能存储少量的数据进行恢复。

  

- Activity中通常会有有那种在其创建的时候获取数据，然后在其销毁的时候释放数据的方法。如果这些放在Activity中的话，在Activity进行重建的时候，会很浪费资源。但是如果方法在ViewModel中的话，Activity的重建将不会导致数据的重复获取。

**当然，屏幕的旋转，你也可以通过configChanges的设置来阻止它的重建。但是其它的有些意外情况Activity也是有可能重建的**



### ViewMode的使用

ViewMode实现Fra'gment之间通信的例子

- **首先创建ViewMode** 任何类继承ViewModel即可

```java
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public Integer count=0;

}
```

代码中的成员变量 count，Activity进行重建时ViewModel数据不会被回收。



- 简介UI界面

  ![image-20210128153246014](image\ViewMode-Lifecycle-Deom.png)

  点击+1 就会+1 点击+2 就会＋2

- MainActivity

  

  ```java
  package com.yinghua;
  
  import androidx.appcompat.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.widget.Button;
  import android.widget.TextView;
  
  public class MainActivity extends AppCompatActivity {
      Button button1,button2;
      TextView textView;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          MyViewModel viewModel = new MyViewModel();//创建viewMode实例
          textView = findViewById(R.id.textView);
          button1= findViewById(R.id.button);
          button2 = findViewById(R.id.button2);
  
          button1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {  
                 textView.setText(String.valueOf(viewModel.count++));
              }
          });
          button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  textView.setText(String.valueOf(viewModel.count+=2));
              }
          });
      }
  }
  ```

实现的效果是 无论是屏幕反转 还是设置 其他语言时 ViewModel的数据不会被回收



### ViewMode SavedStateHandle(即使被后台杀死数据依旧存在)

SavedStateHandle 作用就是设置key value 回到后台数据依旧被保存

- 导入依赖

```
implementation "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0"
```



- 定义常量 SavedStateHandle类型  并且创建它的构造函数 ，判断handle中是否有MyData这样的key 如果没有就创建，返回一个LiveData数据

add()方法是为 key中的value累加

```java
package com.yinghua.viewmode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    final static String KEY="MyData";
    private final SavedStateHandle handle;

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber(){
        if(!handle.contains(KEY)){
            handle.set(KEY,0);
        }
        return handle.getLiveData(KEY);

    }
    public void add(){
        getNumber().setValue(getNumber().getValue()+1);
    }
}

```

- xml 文件使用DataBindidng 

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="data"
            type="com.yinghua.viewmode.MyViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
            android:id="@+id/textView"
            android:layout_width="126dp"
            android:layout_height="0dp"
            android:layout_marginTop="126dp"
            android:layout_marginBottom="184dp"
            android:text="@{String.valueOf(data.getNumber())}"
            android:gravity="center"
            app:layout_constraintBottom_toTopOf="@+id/button"
            app:layout_constraintHorizontal_bias="0.498"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.193" />

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="294dp"
            android:text="@string/_1"
            android:onClick="@{()->data.add()}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textView" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



- ActivityMain 

  - activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main); 

    默认的 替换成 DataBinding的布局方式

    

  - myViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);

    为自定义的MyViewModel初始化

    

  - activityMainBinding.setData(myViewModel);

    将myViewModel设置到Activity_layout.xml文件中 ，在xml文件就可以识别到有myViewModel这样的对象存在，就可以使用对象了

    

  - activityMainBinding.setLifecycleOwner(this);

    使LiveData生效

    

```java
package com.yinghua.viewmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.yinghua.viewmode.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        activityMainBinding.setData(myViewModel);
        activityMainBinding.setLifecycleOwner(this);
        MyViewModel data = activityMainBinding.getData();

        Log.e("TAG",data+" ");

    }
}
```

- 最后效果：即使是翻转屏幕 还是 点击Home键 回到app中 数据没有被销毁

![image-20210129132803591](image\Savehanle.png)



### ViewMode可以访问Shareprferences 文件数据 

永久保存在手机中 重启后不会被清除

使用ViewMode的子类

- AndroidViewMode

  AndroidViewModel内持有Application的引用，所以通常可以做一些全生命周期的工作

  ```java
  package com.yinghua.shareprferences_viewmode;
  
  import android.app.Application;
  import android.content.Context;
  import android.content.SharedPreferences;
  import androidx.annotation.NonNull;
  import androidx.lifecycle.AndroidViewModel;
  import androidx.lifecycle.LiveData;
  import androidx.lifecycle.SavedStateHandle;
  /**
   * 是ViewModel的子类
   * AndroidViewModel内持有Application的引用，所以通常可以做一些全生命周期的工作
   */
  public class MyViewMode extends AndroidViewModel {
      private String KEY = getApplication().getResources().getString(R.string.data_sum);
      private String fileName = getApplication().getResources().getString(R.string.fileName);
      SavedStateHandle handle;
  
      public MyViewMode(@NonNull Application application, SavedStateHandle handle) {
          super(application);
          this.handle = handle;
          if (!handle.contains(KEY)) {
              load();
          }
      }
      public LiveData<Integer> getNumber() {
          return handle.getLiveData(KEY);
  
      }
      private void load() {
          //创建xml文件
          SharedPreferences shp = getApplication().getSharedPreferences(fileName, Context.MODE_PRIVATE);
          int anInt = shp.getInt(KEY, 0);//读取key中的value，如果没有就是0
          handle.set(KEY, anInt);//设置一个key，这个key的类型是String，第二个参数是value
      }
      public void save() {
          SharedPreferences shp = getApplication().getSharedPreferences(fileName, Context.MODE_PRIVATE);
          //获取对SharedPreFerences文件操作对象
          SharedPreferences.Editor edit = shp.edit();
          edit.putInt(KEY, getNumber().getValue());//写入数据key和value
          edit.apply();//应用了才会真的写入
      }
      public void add(int x) {
          handle.set(KEY, getNumber().getValue() + x);
      }
  }
  ```

  - ActivityMain

    ```java
    package com.yinghua.shareprferences_viewmode;
    
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.databinding.DataBindingUtil;
    import androidx.lifecycle.SavedStateViewModelFactory;
    import androidx.lifecycle.ViewModelProvider;
    
    import android.os.Bundle;
    
    import com.yinghua.shareprferences_viewmode.databinding.ActivityMainBinding;
    
    public class MainActivity extends AppCompatActivity {
        MyViewMode myViewMode;
        ActivityMainBinding binding;
    
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_main);
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            myViewMode = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(MyViewMode.class);
            binding.setData(myViewMode);
            binding.setLifecycleOwner(this);
        }
    
        //在Activity的生命周期执行保存方法
        @Override
        protected void onPause() {
            super.onPause();
            myViewMode.save();
      }
    }
    ```

  ```
    
    
  ```



## LiveData 

　LiveData与ViewMode是经常搭配在一起使用的,但是为了不太混乱,我还是拆分开来说明,此篇博客只讲解 LiveData 与 MutableLiveData的概念与使用方式(但是会涉及到ViewMode的部分代码).

### LiveData是干什么的?

　　由于LiveData和MutableLiveData都是一个概念的东西(只是作用范围不同)所以就不重复解释了,直接理解LiveData就可以明白MutableLiveData

　　直接理解LiveData的字面意思是前台数据,其实这其实是很准确的表达.下面我们来说说LiveData的几个特征:

1.首先LiveData其实与数据实体类(POJO类)是一样的东西,它负责暂存数据.

2.其次LiveData其实也是一个观察者模式的数据实体类,它可以跟它注册的观察者回调数据是否已经更新.

3.LiveData还能知晓它绑定的Activity或者Fragment的生命周期,它只会给前台活动的activity回调(这个很厉害).这样你可以放心的在它的回调方法里直接将数据添加到View,而不用担心会不会报错.(你也可以不用费心费力判断Fragment是否还存活)

### LiveData与MutableLiveData区别

LiveData与MutableLiveData的其实在概念上是一模一样的.唯一几个的区别如下:

1.MutableLiveData的父类是LiveData

2.LiveData在实体类里可以通知指定某个字段的数据更新.

3.MutableLiveData则是完全是整个实体类或者数据类型变化后才通知.不会细节到某个字段



### Demo

- 定义ViewModel类

```java
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModeLiveData extends ViewModel {
    private MutableLiveData<Integer> liveNumber;

    /**
     * 为保证 Number 不为空 需要赋值
     *  第一种方式 创建构造方法 当类被创建时给成员变量赋值
     *      不高效
     * */
  /*  ViewModeLiveData(){
        liveNumber =new MutableLiveData<>();
        liveNumber.setValue(0);
    }*/

    //第二种方式 :通过 get方法判断赋值
    public MutableLiveData<Integer> getLiveNumber() {
        if(liveNumber==null){
            liveNumber = new MutableLiveData<>();
            liveNumber.setValue(0);
        }
        return liveNumber;
    }

    //累加
    public void  addLvieNumber(Integer a){
        liveNumber.setValue(liveNumber.getValue()+a);
    }
}

```



- UI设置

AndroidStudio自带的矢量图 

在res目录右键点击new 

点击vector Asset 即可创建选中矢量图





![](\image\ViewMode-liveDataDome.png)

- MainActivity

```java
package com.yinghua.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewModeLiveData viewModeLiveData;
    TextView textView;
    ImageButton button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.imageButton);
        button2 = findViewById(R.id.imageButton2);

        //绑定自定义的LiveData
        viewModeLiveData=ViewModelProviders.of(this).get(ViewModeLiveData.class);

        //监听liveData数据 如果发生变化 调用 onChanged（）方法
        viewModeLiveData.getLiveNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModeLiveData.addLvieNumber(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModeLiveData.addLvieNumber(-1);
            }
        });


        /**
         * 对于这样的设计：
         * 如果ViewMode有数百个属性每个都这么设计实在是太麻烦了，但是更规范了
         * */
    }
}
```

## DataBinding 介绍



**DataBinding**是一个支持库，顾名思义：**数据绑定**，它可以将布局页面中的组件与应用中的数据绑定，它支持单向绑定与双向绑定，所谓单向绑定是指数据的变化会驱动页面的变化。而双向绑定除此之外还支持页面的变化驱动数据的变化，比如页面中有一个 `EditText`，数据的变化可以改变它的显示内容，我们输入的内容也可以改变绑定的数据。

> **DataBinding**只是一种工具，它解决的是View和数据之间的绑定。MVVM是一种架构模式，两者是有本质区别的。

使用前提是需要在buuid.gradle文件添加 :

```groovy
 dataBinding {
        enabled = true
    }
```

如图所示：

![](F:\Android\image\databinding.png)



### Demo

这次使用实体类、ViewMode和LiveData联合使用

- 创建实体类Student

```java
package com.yinghua.databindingtest.pojo;

public class Student {
    private String id;
    private String name;
    private String age;

    public Student() {
    }

    public Student(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

```



- 将实体类封装到  MutableLiveData<Student>中 让ViewMode管理

创建MyViewMode类

```java
package com.yinghua.databindingtest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yinghua.databindingtest.pojo.Student;

public class MyViewMode extends ViewModel {
    private MutableLiveData<Student> studentMutableLiveData;

    //初始化
    public MutableLiveData<Student> getStudentMutableLiveData() {
        if(studentMutableLiveData==null){
            studentMutableLiveData=new MutableLiveData<>();
            studentMutableLiveData.setValue(new Student("2","李四","23"));
        }
        return studentMutableLiveData;
    }
    
    //添加
    public void addStudent(){
        studentMutableLiveData.postValue(new Student("4","田七","22"));
    }
}

```



- ActivityMain 的页面布局

```xml
<?xml version="1.0" encoding="utf-8"?>

<!--使用Binding 布局-->
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <!--
        可以使用表达式
		variable 声明一个变量 
			name="xxx"变量名
			type="全类名" 变量的类型

		lambda表达式
        @{}使用java语法
        @{（）-> xxx.xxx()} 调用方法
    -->
    <data>
        <variable
            name="data"
            type="com.yinghua.databindingtest.MyViewMode" />

    </data>

    <!--正常的页面布局-->
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
            android:id="@+id/textView"
            android:layout_width="162dp"
            android:layout_height="38dp"
            android:text="@{data.studentMutableLiveData.id}"
            android:gravity="center"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.165" />

        <TextView
            android:id="@+id/textView2"
            android:layout_width="162dp"
            android:layout_height="38dp"
            android:text="@{data.studentMutableLiveData.name}"
            android:gravity="center"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.497"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.317" />

        <TextView
            android:id="@+id/textView3"
            android:layout_width="162dp"
            android:layout_height="38dp"
            android:text="@{data.studentMutableLiveData.age}"
            android:gravity="center"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.497"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.467" />

        <Button
            android:id="@+id/button"
            android:layout_width="122dp"
            android:layout_height="66dp"
            android:text="@string/button"
            android:onClick="@{()->data.addStudent()}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.498"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.649" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

效果：



- ActivityMain类

```java
package com.yinghua.databindingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yinghua.databindingtest.databinding.ActivityMainBinding;
import com.yinghua.databindingtest.pojo.Student;

public class MainActivity extends AppCompatActivity {
    MyViewMode myViewMode;


    ActivityMainBinding binding;//有了这个就是dataBinding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //binding 就有 textview 和 button...
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);//使用dataBinding
        myViewMode=ViewModelProviders.of(this).get(MyViewMode.class);
        binding.setData(myViewMode);//将值设置到xml中
        binding.setLifecycleOwner(this);//这是开启liveData的作用

//        myViewMode.getStudentMutableLiveData().observe(this, new Observer<Student>() {
//            @Override
//            public void onChanged(Student student) {
//                binding.textView.setText(student.getId());
//                binding.textView2.setText(student.getName());
//                binding.textView3.setText(student.getAge());
//            }
//        });


      /*
        使用 DataBinding 甚至不需要在写点击事件，可以在 activity中写入
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewMode.getStudentMutableLiveData().setValue(new Student("2","王五","33"));
            }
        });*/

    }
}
```



2021年1月28日 18时00分 



## Navigation导航

下面先理解4个概念

- Navhost

  - Navhost是一个容器,存放页面，同时是一个控制器 （其实存放ragment容器）

- Fragment

  - Fragment早期是为了适用大屏幕，为了实现屏幕上的一小块内容分割多个小内容 

- NavController

  - 控制导航逻辑，驱动页面

- NavGraph

  - 使用图形化梳理页面之间的关系，Navgraph是一个资源文件

    

### Navigation+bottomnavigation 控件使用

- 首先在res目录下创建一个menu文件 

  - bottom_nav_menu.xml

    - android:id 必须严格命名  
    - android:icon 是在drawable目录下的矢量图（怎么创建看我01文章）  
    - android:titlevalus下面的strings.xml文件中的 key value 形式字符串也是严格命名

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android">
    
        <item
            android:id="@+id/navigation_home"
            android:icon="@drawable/ic_home_black_24dp"
            android:title="@string/title_home" />
    
        <item
            android:id="@+id/navigation_dashboard"
            android:icon="@drawable/ic_dashboard_black_24dp"
            android:title="@string/title_dashboard" />
    
        <item
            android:id="@+id/navigation_notifications"
            android:icon="@drawable/ic_notifications_black_24dp"
            android:title="@string/title_notifications" />
    
        <item
            android:id="@+id/navigation_blank_fragment"
            android:icon="@drawable/ic_notifications_black_24dp"
            android:title="@string/title_blank" />
    
    </menu>
    ```

    大致结构

    ![](image\navigation1.png)

- 创建Fragment+ViewModel  menu中有多少个item就创建多少个

  ​	![](image\navigation2.png)





- 还需要常见一个navigation的视图文件xml
  - res目录创建 AndroidResoucesFile, Resource type选择Navigation

![](\image\NavigationCreate.png)

创建完成后(id也要严格对应 menu的id)

![](image\navigation3.png)





- 回到activity_main.xml中

  - 添加布局控件BottomNavigationView 添加属性 app:menu="@menu/xxx" ，完后可以直观的看到buttom的导航栏了

    ![](image/navigation4.png)

  - 还需要添加NavHostFragment，这玩意就是一个fragment的容器

    - 添加时如果已经创建了navigation资源文件会出现选着一个navigation视图，选中添加即可

      ![](image/navigation5.png)



------

**完成上面的之后，下面就编写代码**

来到MainActivity.java



```java
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //获取控件对象
            BottomNavigationView navView = findViewById(R.id.nav_view);
         
            //获取每一个Fragment的所在文件配置
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                //获取的即是menu的itme的id 也是 navigation对应 id
                    R.id.navigation_home, 
                	R.id.navigation_dashboard,
                	R.id.navigation_notifications,
                	R.id.navigation_blank_fragment)
                    .build();//
            
            //获取navcontroller对象实现逻辑跳转
            //this 是当前的活动（Activity）,获取容器控件 就是 NavHostFragment控件id
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            
            //this 是当前的活动（Activity）,放入navcotroller对象，放入Fragment的所在文件配置对象
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);//设置操作栏与导航控制器
            
            //BottomNavigationView控件对象，放入navcotroller对象，
            NavigationUI.setupWithNavController(navView, navController);//设置导航控制器
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
```

校验：按住ctrl键+鼠标左键 ,出现面的就对了，一个一个的试试

![](image/navigation6.png)





## ViewPager2



### 关于ViewPager2

与ViewPager一样（它的弟弟），ViewPager2需要一个适配器来填充页面。任何RecyclerView。当您的页面没有需要跨活动生命周期维护的状态时，适配器将满足简单的用例。将为页面膨胀的顶层视图必须将其layout_width和layout_height设置为match_parent。如果你的页面有为了节省空间，使用ViewModel；

简单来说它需要 Fragment 配合使用 ，需要的icon 使用TableLayout布局页面使用（文字语言能力，下面会用案例测试）

 ViewPager2是Google 在 androidx 组件包里增加的一个组件，目前已经到了1.0.0-beta02版本。

官方最新更新时间是 ：2020 年 4 月 1 日 1.0.0稳定版本

- grdle依赖

```groovy
 dependencies {
        implementation "androidx.viewpager2:viewpager2:1.0.0"
     
     	//还需要用到 material 作用就是让页面 bulingbuling的 
     	// https://mvnrepository.com/artifact/com.google.android.material/material
	    implementation group: 'com.google.android.material', name: 'material', version: '1.2.1'

	   	 implementation 'androidx.appcompat:appcompat:1.1.0-alpha05'
    }
```



- 首先创建布局

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical"
      android:background="@drawable/ic_app_indexmain_bg"
      tools:context="com.yinghua.viewpager.bottom.show.ShowFragment">
  
      <!-- 适配线（自己命名的。。。）-->
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="horizontal"
          app:layout_constraintGuide_percent="0.07" />
  	<!-- 适配线-->
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline2"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="vertical"
          app:layout_constraintGuide_percent="0.01" />
  
      <!-- 适配线-->
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline3"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="horizontal"
          app:layout_constraintGuide_percent="0.0" />
  
      <!-- 适配线-->
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline4"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="vertical"
          app:layout_constraintGuide_percent="0.99" />
  
      	<!-- 线性布局-->
      <LinearLayout
          android:id="@+id/linearLayout2"
          android:layout_width="380dp"
          android:layout_height="116dp"
          android:orientation="horizontal"
          android:gravity="center"
          app:layout_constraintBottom_toTopOf="@+id/guideline"
          app:layout_constraintEnd_toStartOf="@+id/guideline4"
          app:layout_constraintStart_toStartOf="@+id/guideline2"
          app:layout_constraintTop_toTopOf="@+id/guideline3">
  
    		
          <ImageView
              android:id="@+id/touxing"
              android:layout_width="100dp"
              android:layout_height="50dp"
              android:layout_gravity="center"
              android:layout_weight="1"
  
              android:src="@drawable/ic_mybox"
              />
  
          <ImageButton
              android:layout_width="250dp"
              android:layout_height="30dp"
              android:layout_gravity="center"
              android:layout_weight="1"
              android:background="@drawable/round_corners_bg"
              app:srcCompat="@drawable/ic_baseline_search_24"
              tools:ignore="VectorDrawableCompat" />
  
  
          <ImageView
              android:layout_width="100dp"
              android:layout_height="50dp"
              android:layout_gravity="center"
              android:layout_weight="1"
              android:scaleType="centerCrop"
              android:src="@drawable/ic_myorder"
              />
  
  
          <ImageView
              android:layout_width="100dp"
              android:layout_height="50dp"
              android:layout_gravity="center"
              android:layout_weight="1"
              android:scaleType="centerCrop"
              android:src="@drawable/ic_notifications_black_24dp"
              />
  
  
      </LinearLayout>
  
      <TextView
          android:id="@+id/text_show"
          android:layout_width="0dp"
          android:layout_height="0dp"
          android:textAlignment="center"
          android:textSize="20sp"
          tools:layout_editor_absoluteX="4dp"
          tools:layout_editor_absoluteY="4dp" />
  
  
      <com.google.android.material.tabs.TabLayout
          android:id="@+id/tableLayout"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          app:layout_constraintEnd_toStartOf="@+id/guideline4"
          app:layout_constraintStart_toStartOf="@+id/guideline2"
          app:layout_constraintTop_toTopOf="@+id/guideline">
  
          <com.google.android.material.tabs.TabItem
              android:id="@+id/table1"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="Monday" />
  
          <com.google.android.material.tabs.TabItem
              android:id="@+id/table2"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="Tuesday" />
  
          <com.google.android.material.tabs.TabItem
              android:id="@+id/table3"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="Wednesday" />
  
          <com.google.android.material.tabs.TabItem
              android:id="@+id/table4"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="Wednesday" />
  
          <com.google.android.material.tabs.TabItem
              android:id="@+id/table5"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="Wednesday" />
      </com.google.android.material.tabs.TabLayout>
  
  
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline5"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="horizontal"
          app:layout_constraintGuide_percent="1" />
  
      <LinearLayout
          android:layout_width="match_parent"
          android:layout_height="0dp"
          android:layout_marginTop="18dp"
          android:orientation="vertical"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toStartOf="@+id/guideline4"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/linearLayout2">
          <androidx.viewpager2.widget.ViewPager2
              android:id="@+id/viewPager2"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              app:layout_constraintBottom_toBottomOf="parent">
  
          </androidx.viewpager2.widget.ViewPager2>
  
      </LinearLayout>
  
  
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

  



## Paging 分页库

Paging 库可帮助您一次加载和显示多个小的数据块。按需载入部分数据会减少网络带宽和系统资源的使用量。

是Jetpack中众多的工具之一，下拉列表加载更多是安卓开发中使用频率很高的一个功能，而且配合Jetpack中的LiveData、Room食用更佳。

- build.gradle下导入依赖

```groovy
  	//paging分页库
    def paging_version = "2.1.2"
    implementation "androidx.paging:paging-runtime:$paging_version"

    //room数据库
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
```

## 界面

- activity_main.xml

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <androidx.constraintlayout.widget.Guideline
          android:id="@+id/guideline"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:orientation="horizontal"
          app:layout_constraintGuide_percent="0.9056088" />
  
      <Button
          android:id="@+id/buttonPopulate"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="生成"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toStartOf="@+id/buttonClear"
          app:layout_constraintHorizontal_bias="0.5"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="@+id/guideline" />
  
      <Button
          android:id="@+id/buttonClear"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="清空"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.5"
          app:layout_constraintStart_toEndOf="@+id/buttonPopulate"
          app:layout_constraintTop_toTopOf="@+id/guideline" />
  
      <androidx.recyclerview.widget.RecyclerView
          android:id="@+id/recyclerView"
          android:layout_width="0dp"
          android:layout_height="0dp"
          app:layout_constraintBottom_toTopOf="@+id/guideline"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

  - 再新建一个layout 叫cell.xml

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    
        <ImageView
            android:id="@+id/imageView"
            android:layout_width="364dp"
            android:layout_height="123dp"
            android:layout_marginTop="4dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:srcCompat="@tools:sample/avatars" />
    
        <TextView
            android:id="@+id/textView"
            android:layout_width="270dp"
            android:layout_height="47dp"
            android:layout_marginStart="16dp"
            android:layout_marginLeft="16dp"
            android:gravity="center"
            android:text="TextView"
            android:textSize="24sp"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView" />
    
        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="查看"
            android:textAllCaps="false"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.264"
            app:layout_constraintStart_toEndOf="@+id/textView"
            app:layout_constraintTop_toBottomOf="@+id/imageView" />
    </androidx.constraintlayout.widget.ConstraintLayout>
    ```

    

## 创建数据库

- 创建bean bean就是表 

```java

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "student_table") //根据bean创建表
public class Student {
    @PrimaryKey(autoGenerate = true)//主键自增
    private int id;
    @ColumnInfo(name = "student_number") //字段
    private int studentNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}

```



- dao 接口

  ```java
  
  import androidx.paging.DataSource;
  import androidx.room.Dao;
  import androidx.room.Insert;
  import androidx.room.Query;
  
  @Dao
  public interface StudentDao {
      //插入多条记录
      @Insert 
      void insertStudents(Student... students);
  
      //删除表中所有记录
      @Query("DELETE FROM student_table")
      void deleteAllStudents();
  
      //查询表中所有的记录
      @Query("SELECT * FROM student_table ORDER BY id")
      DataSource.Factory<Integer, Student> getAllStudents();
  }
  ```

  

- 数据库初始化 和实现dao接口

  ```java
  package com.yinghua.pagedemo;
  
  import android.content.Context;
  
  import androidx.room.Database;
  import androidx.room.Room;
  import androidx.room.RoomDatabase;
  //           添加表						版本			是否导出文件
  @Database(entities = {Student.class}, version = 1, exportSchema = false)
  public abstract class StudentsDatabase extends RoomDatabase {
      private static StudentsDatabase instance;
  
      //当new 时初始化数据库
      static synchronized StudentsDatabase getInstance(Context context) {
          if (instance == null) {
              instance = Room.databaseBuilder(context, StudentsDatabase.class, "students_database")
                      .build();
          }
          return instance;
      }
  
      //Room 自动实现dao接口功能
      abstract StudentDao getStudentDao();
  }
  ```





PagedAdapter(分页适配器)

```java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


public class MyPagedAdapter extends PagedListAdapter<Student, MyPagedAdapter.MyViewHolder> {
    //初始化适配器时调用
    public MyPagedAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            
            //两个 item 是否相等
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            //两个 item 内容是否相等
            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }

    //给layout传入view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell, parent, false);
        return new MyViewHolder(view);
    }

    //绑定视图
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //第postion个位置的item为Student
        Student student = getItem(position);
        
        //因为是分页所有会有空值
        if (student == null) {
            holder.textView.setText("loading");
            holder.imageView.setImageResource(R.drawable.ic_baseline_5g_24);
            holder.button.setText("test");
        } else {
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
            holder.imageView.setImageResource(R.drawable.ic_baseline_4k_24);
            holder.button.setText("test");
        }
    }

  
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.button = itemView.findViewById(R.id.button);
        }
    }
}
```



- 页面加载

  ```java
  package com.yinghua.pagedemo;
  
  import android.os.AsyncTask;
  import android.os.Bundle;
  import android.util.Log;
  import android.view.View;
  import android.widget.Button;
  
  import androidx.appcompat.app.AppCompatActivity;
  import androidx.lifecycle.LiveData;
  import androidx.lifecycle.Observer;
  import androidx.paging.LivePagedListBuilder;
  import androidx.paging.PagedList;
  import androidx.recyclerview.widget.DividerItemDecoration;
  import androidx.recyclerview.widget.LinearLayoutManager;
  import androidx.recyclerview.widget.RecyclerView;
  
  
  public class MainActivity extends AppCompatActivity {
      RecyclerView recyclerView; //布局 layout
      Button buttonPopulate, buttonClear; //button按钮
      StudentDao studentDao;//到接口共功能
      StudentsDatabase studentsDatabase;//数据库
      MyPagedAdapter pagedAdapter; //适配器
      LiveData<PagedList<Student>> allStudentsLivePaged;//LivaData数据
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          recyclerView = findViewById(R.id.recyclerView);
  
          //LinearLayoutManager(this)里面的参数，第二个参数表示水平布局，第三个参数表示是否反转，效果呈现
          recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
  
          //设置水平分割线
          recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
  
          //分页适配器
          pagedAdapter = new MyPagedAdapter();
          recyclerView.setAdapter(pagedAdapter);
  
          //初始化数据库
          studentsDatabase = StudentsDatabase.getInstance(this);
  
          //实现dao接口的功能并返回dao对象
          studentDao = studentsDatabase.getStudentDao();
  
          //数据容器，将查询到的所有student数据,每次加入两个 ，赋值给 allStudentsLivePaged
          allStudentsLivePaged = new LivePagedListBuilder<>(studentDao.getAllStudents(), 2)
                  .build();
  
  
          allStudentsLivePaged.observe(this, new Observer<PagedList<Student>>() {
  
              //当数据发生变化时调用这个方法
              @Override
              public void onChanged(final PagedList<Student> students) {
                  pagedAdapter.submitList(students);
                  students.addWeakCallback(null, new PagedList.Callback() {
                      @Override
                      public void onChanged(int position, int count) {
                          Log.e("myLog", "onChanged: " + students);
                      }
  
                      @Override
                      public void onInserted(int position, int count) {
  
                      }
                      @Override
                      public void onRemoved(int position, int count) {
  
                      }
                  });
              }
          });
  
          //给 button添加点击事件
          buttonPopulate = findViewById(R.id.buttonPopulate);
          buttonClear = findViewById(R.id.buttonClear);
  
          
          buttonPopulate.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Student[] students = new Student[1000];
                  for (int i = 0; i < 1000; i++) {
                      Student student = new Student();
                      student.setStudentNumber(i);
                      students[i] = student;
                  }
                  //执行异步任务
                  new InsertAsyncTask(studentDao).execute(students);
                  new InsertAsyncTask(studentDao).execute();
              }
          });
  
          buttonClear.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //执行异步任务
                  new ClearAsyncTask(studentDao).execute();
              }
          });
  
  
      }
  
      
      //内部类 插入操作
      static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
          StudentDao studentDao;
          
          //内部类的方法
          public InsertAsyncTask(StudentDao studentDao) {
              this.studentDao = studentDao;
          }
  
          //在后台异步任务 调用dao执行insert操作
          @Override
          protected Void doInBackground(Student... students) {
              studentDao.insertStudents(students);
              return null;
          }
      }
  
      //内部类 清空操作
      static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
          StudentDao studentDao;
  
          //内部类的方法
          public ClearAsyncTask(StudentDao studentDao) {
              this.studentDao = studentDao;
          }
  
          //在后台异步任务 调用dao执行delete
          @Override
          protected Void doInBackground(Void... voids) {
              studentDao.deleteAllStudents();
              return null;
          }
      }
  }
  ```

## 最后效果

https://www.kuangstudy.com/bbs/1358032275756863489

2021 2.6 20 ：30

