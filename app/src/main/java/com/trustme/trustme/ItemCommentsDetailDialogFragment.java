package com.trustme.trustme;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemCommentsDetailDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemCommentsDetailDialogFragment extends BottomSheetDialogFragment {

    //Variale de clase constante
    private static final String KEY_HISTORY_ID = "historyId";

    private EditText editTextWriteComment;

    private RecyclerView recyclerListComments;
    private ArrayList<Comments> listComments;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference commentsReference;

    private CommentsAdapter adapter;

    // TODO: Customize parameters
    public static ItemCommentsDetailDialogFragment newInstance(String historyId) {
        final ItemCommentsDetailDialogFragment fragment = new ItemCommentsDetailDialogFragment();

        //Diccionario o mapa = ES Key - Value (int, String, Boolean, Char)
        //Permiten las asociaciones
        final Bundle args = new Bundle();

        args.putString(KEY_HISTORY_ID, historyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_comments_detail_dialog_list_dialog,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = view.findViewById(R.id.list);

        Button btnShareComment = view.findViewById(R.id.btnShareComment);
        editTextWriteComment = view.findViewById(R.id.editTextWriteComment);


        Bundle diccionarioValores = getArguments();

        //TODO falta utilizar el valor de la variable
        //Se van a consultar los datos del libro
        String historyId = diccionarioValores.getString(KEY_HISTORY_ID);

        firebaseDatabase = FirebaseDatabase.getInstance();

        ArrayList<Comments> comments = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CommentsAdapter(comments);
        recyclerView.setAdapter(adapter);

        getComments(historyId);

        btnShareComment.setOnClickListener(this::sendComment);
    }


    private void getComments(String historyId) {

        //La diferencia aquí es que cargamos los datos que están en una referencia hija o nodo
        //La referencia hija les da por un nodo que tiene el identificador
        //Esto nos permitirá cargar todos los comentrios de ese objeto
        commentsReference = firebaseDatabase.getReference("comments");

        commentsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()) {
                    Comments comment = data.getValue(Comments.class);
                    adapter.addComment(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void sendComment(View view) {
        String txtComment = editTextWriteComment.getText().toString().trim();
        if(!txtComment.isEmpty()) {
            Comments comments = new Comments(txtComment);
            saveComment(comments);
        }else {
            Toast.makeText(getActivity(), "Please enter your content",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void saveComment(Comments comment) {
        String commentId = commentsReference.push().getKey();

        comment.setId(commentId);

        Date now = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //TODO UTC
        //2021-05-11 16:42:12
        String createdAt = formateador.format(now);
        comment.setDate(createdAt);

        commentsReference.child(commentId).setValue(comment)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    "Comment sucessfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Error. try again later",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}