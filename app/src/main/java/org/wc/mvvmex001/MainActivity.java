package org.wc.mvvmex001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private static final String TAG = "MainActivity";
    private NoteAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리사이클러 뷰 만드는 방법
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //메인액티비티(this)와 뷰모델(NoteViewModel) 연결
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        //옵저버 걸기(context ,함수를 넣는다.) 데이터가 변할때마다 실행할 함수
        noteViewModel.구독하기().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.d(TAG, "onChanged: 구독하고 있는 데이터가 변경되었습니다.");
                adapter.setNotes(notes);//한건 추가,삭제,수정 -> 덮어씌움
            }
        });
    }
}