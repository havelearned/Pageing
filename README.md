
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

