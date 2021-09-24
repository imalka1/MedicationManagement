package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import lk.drugreminder.R;
import lk.drugreminder.adapter.SicknessAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Reason;

public class ReminderAddReasonFragment extends Fragment {

    private View view;
    private Button btnDisease;
    private DatabaseReference dbDisease;
    private EditText txtDisease;
    private RecyclerView recyclerView;
    private SicknessAdapter sicknessAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_new_reason, container, false);

        dbDisease = FirebaseDB.getDBReason();

        txtDisease = view.findViewById(R.id.txt_medication);
        btnDisease = view.findViewById(R.id.btn_add_disease);

        btnDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtDisease.getText().toString().equals("")) {
                    addDisease();
                } else {
                    Toast.makeText(getContext(), "Please enter a sickness", Toast.LENGTH_LONG).show();
                }
            }
        });

        // loadSicknesses();

        return view;
    }

    private void addDisease() {
        String id = dbDisease.push().getKey();
        Reason reason = new Reason(id, txtDisease.getText().toString());
//        dbDisease.push().setValue(sickness);
        dbDisease.child(id).setValue(reason);
        txtDisease.setText("");
        Toast.makeText(getContext(), "Reason added successfully", Toast.LENGTH_LONG).show();
    }

}
