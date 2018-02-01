package com.oliver.coinmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oliver.coinmarket.klasi.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogIn extends AppCompatActivity {
    @BindView(R.id.textIme)
    TextView textView;
    @BindView(R.id.editIme)
    EditText Ime;
    @BindView(R.id.editPrezime)
    EditText prezime;
    @BindView(R.id.kopceSave)
    Button save;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String TAG = LogIn.class.getSimpleName();
    private String usedId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Realtime");
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG,"App Title updated");

                Log.e(TAG,dataSnapshot.getValue(String.class));
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"failed to read app", databaseError.toException());

            }
        });

    }
    @OnClick(R.id.kopceSave)
    public void Klik(View view){
        String name = Ime.getText().toString();
        String mail = prezime.getText().toString();
        if (TextUtils.isEmpty(usedId)){
            createUser(name,mail);
        } else {
            updateUser(name,mail);
        }
    }

    private void updateUser(String name, String mail) {
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(usedId).child("name").setValue(name);
        if (!TextUtils.isEmpty(mail))
            mFirebaseDatabase.child(usedId).child("mail").setValue(mail);

    }

    private void createUser(String name, String mail) {
        if (TextUtils.isEmpty(usedId)){
            usedId = mail.replace(".","");
        }
        User user = new User(name,mail);
        mFirebaseDatabase.child(usedId).setValue(user);
//        Intent intent = new Intent(this,Main2Activity.class);
//        startActivity(intent);

        addUserChangeListener();

    }
    private void addUserChangeListener(){
        mFirebaseDatabase.child(usedId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user==null){
                    Log.e(TAG,"User data is null");
                    return;
                }
                Log.e(TAG,"User data is changed"+user.name+","+user.email);
                textView.setText(user.name+","+user.email);

//                da se isprazni edittext
                Ime.setText("");
                prezime.setText("");
                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"failed to read app", databaseError.toException());
            }
        });
    }

    private void toggleButton(){
        if (TextUtils.isEmpty(usedId)){
            save.setText("Save");
        } else {
            save.setText("Update");
        }
    }
}
