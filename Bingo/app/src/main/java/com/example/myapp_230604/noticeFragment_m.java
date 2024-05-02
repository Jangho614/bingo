package com.example.myapp_230604;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class noticeFragment_m extends Fragment {

    RecyclerView recyclerView;
    EditText editTextSearch;
    NoticeAdapter adapter;
    Button write_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_m, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        editTextSearch = view.findViewById(R.id.editTextSearch);
        write_btn = view.findViewById(R.id.buttonnn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoticeAdapter(); // Adapter 인스턴스 생성
        recyclerView.setAdapter(adapter);

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    String searchText = editTextSearch.getText().toString().trim();
                    adapter.getFilter().filter(searchText); // 어댑터에 검색어 전달하여 필터링
                    return true;
                }
                return false;
            }
        });

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), board_writing.class);
                startActivity(intent);
            }
        });


        // 기본 데이터 추가
        adapter.addItem(new NoticeAdapter.Item("apple", "010-1000-1000"));
        adapter.addItem(new NoticeAdapter.Item("pineapple", "010-2000-2000"));
        adapter.addItem(new NoticeAdapter.Item("watermelon", "010-3000-3000"));
        adapter.addItem(new NoticeAdapter.Item("melon", "010-1000-1000"));
        adapter.addItem(new NoticeAdapter.Item("banana", "010-2000-2000"));
        adapter.addItem(new NoticeAdapter.Item("orange", "010-3000-3000"));

        return view;
    }

}
