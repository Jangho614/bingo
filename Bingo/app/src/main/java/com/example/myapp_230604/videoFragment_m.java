package com.example.myapp_230604;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class videoFragment_m extends Fragment {

    RecyclerView recyclerView;
    EditText editTextSearch;
    VideoAdapter adapter;

    CheckBox NeedBox;
    CheckBox CompleteBox;
    ImageView imageViewSearch;
    String[] checkSelect = {"", "처리완료", "처리필요"};
    String searchText = "";
    int idx;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_m, container, false);
        idx = 0;
        recyclerView = view.findViewById(R.id.video_recycler);
        editTextSearch = view.findViewById(R.id.search_video);
        imageViewSearch = view.findViewById(R.id.imageView2);
        NeedBox = view.findViewById(R.id.NeedCheck);
        CompleteBox = view.findViewById(R.id.CompleteCheck);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new VideoAdapter(); // Adapter 인스턴스 생성
        recyclerView.setAdapter(adapter);

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = editTextSearch.getText().toString().trim();
                adapter.getFilter().filter(checkSelect[idx] + " " + searchText); // 어댑터에 검색어 전달하여 필터링
            }
        });
        NeedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CompleteBox.setChecked(false);
                    adapter.getFilter().filter(searchText + " 처리필요");
                } else {
                    adapter.getFilter().filter(searchText);
                }
            }
        });

        CompleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NeedBox.setChecked(false);
                    adapter.getFilter().filter(searchText + " 처리완료");
                } else {
                    adapter.getFilter().filter(searchText);
                }
            }
        });

        // 기본 데이터 추가
        adapter.addItem(new VideoAdapter.Item("apple", "2024/03/30 16:43:12","처리완료"));
        adapter.addItem(new VideoAdapter.Item("pineapple", "2024/03/30 17:10:34","처리필요"));
        adapter.addItem(new VideoAdapter.Item("watermelon", "2024/03/31 16:06:31","처리완료"));
        adapter.addItem(new VideoAdapter.Item("melon", "2024/03/31 18:06:58","처리완료"));
        adapter.addItem(new VideoAdapter.Item("banana", "010-2000-2000","처리필요"));
        adapter.addItem(new VideoAdapter.Item("orange", "010-3000-3000","처리필요"));

        return view;
    }
}