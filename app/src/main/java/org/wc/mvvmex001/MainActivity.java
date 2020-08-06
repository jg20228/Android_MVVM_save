package org.wc.mvvmex001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리사이클러 뷰 만드는 방법
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //메인액티비티(this)와 뷰모델(NoteViewModel) 연결
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        //옵저버 걸기(context ,함수를 넣는다.) 데이터가 변할때마다 실행할 함수
        noteViewModel.구독하기().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.d(TAG, "onChanged: 구독하고 있는 데이터가 변경되었습니다.");
                adapter.setNotes(notes);//한건 추가,삭제,수정 -> 덮어씌움
            }
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int priority = new Random().nextInt(100)+1;
                noteViewModel.추가하기(
                        //priority 번호 1
                        new Note("제목"+priority,"설명"+priority, priority)
                );
            }
        });
    }
}