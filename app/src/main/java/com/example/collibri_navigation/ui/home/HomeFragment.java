package com.example.collibri_navigation.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.collibri_navigation.R;
import com.example.collibri_navigation.TovarAddClass;
import com.example.collibri_navigation.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final String GROUPKEY = "Tovar";
    private ListView tovarList;
    private ArrayAdapter<String> adapter;
    private List<String> listData, prom;
    private List<TovarAddClass> listTemp;
    private DatabaseReference mBase;
    private String id, currentUser, UserName;
    private ImageView BtnSettingProduct, tab4;
    private String spin;
    private Integer edMin, edMax;
    private EditText editTextMin, editTextMax;
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        init();
        View v = inflater.inflate(R.layout.activity_main,container,false);
        tovarList = v.findViewById(R.id.tovarList);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void init() {
        tovarList = getView().findViewById(R.id.tovarOrderList);
        listData = new ArrayList<>();
        listTemp = new ArrayList<TovarAddClass>();
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listData);
        tovarList.setAdapter(adapter);
        mBase = FirebaseDatabase.getInstance().getReference(GROUPKEY);
        getDataFromDB();
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0) listData.clear();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    TovarAddClass tovar = ds.getValue(TovarAddClass.class);
                    assert tovar != null;
                    if (tovar.editCategory.equals(spin)) {
                        listData.add(tovar.editNazvanie);
                        listTemp.add(tovar);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mBase.addValueEventListener(vListener);
    }

    private void getDataFromDB2() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listData.size() > 0) listData.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    TovarAddClass tovar = ds.getValue(TovarAddClass.class);
                    assert tovar != null;
                    if (tovar.editCategory.equals(spin)) {
                        listData.add(tovar.editNazvanie);
                        listTemp.add(tovar);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mBase.addValueEventListener(vListener);
    }
}