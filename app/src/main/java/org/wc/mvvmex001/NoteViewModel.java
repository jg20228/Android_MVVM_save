package org.wc.mvvmex001;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    // ViewModel이 가진 데이터!!
    private LiveData<List<Note>> allNotes; //옵저버가 이것을 계속 지켜봄 LiveData<>로 감쌈
    //private List<Note> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
    }

    //빨대연결 호출하면 빨대(Stream)이 꼽히는 것!
    public LiveData<List<Note>> 구독하기(){
        return allNotes;
    }
}
