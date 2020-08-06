package org.wc.mvvmex001;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

//나중에 Room 연결하면 바뀔수도
public class NoteRepository {

    public NoteRepository() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("제목","설명",0));
        allNotes.setValue(notes);
    }

    //DB에서 가져왔다고 생각하고
    private MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();
    //LiveData는 New해서 만드는게 아니라 선언만 해놓고
    //DB에서 가져오는것이기 때문에 new해서 만드는게 아님
    //만약 new해서 만들고 싶으면 2가지 방법이 있다.
    //MutableLiveData 변할수 있는 데이터, LiveData는 내가 변경할수 없는 데이터
    //LiveData는 함수를 getter만 있음
    //MutableLiveData는 함수를 getter, setter를 가지고 있음.
    //데이터베이스에 스트림(빨대)연결했을때는 무조건 LiveData
    //Retropit으로 통신할때는 무조건 MutableLiveData를 사용해야함.

    public void delete(Note note){
        List<Note> notes = allNotes.getValue();
        notes.remove(note);
        allNotes.setValue(notes);
    };

    public void save(Note note){
        List<Note> notes = allNotes.getValue();
        notes.add(note);
        allNotes.setValue(notes);
    };

    public LiveData<List<Note>> findAll(){
        return allNotes;
    }
}
