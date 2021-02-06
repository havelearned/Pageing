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
    RecyclerView recyclerView;
    Button buttonPopulate, buttonClear;
    StudentDao studentDao;
    StudentsDatabase studentsDatabase;
    MyPagedAdapter pagedAdapter;
    LiveData<PagedList<Student>> allStudentsLivePaged;

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
                new InsertAsyncTask(studentDao).execute(students);
                new InsertAsyncTask(studentDao).execute();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearAsyncTask(studentDao).execute();
            }
        });


    }

    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudents(students);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;

        public ClearAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudents();
            return null;
        }
    }
}