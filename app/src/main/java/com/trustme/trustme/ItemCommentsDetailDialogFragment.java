package com.trustme.trustme;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemCommentsDetailDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemCommentsDetailDialogFragment extends BottomSheetDialogFragment
        implements CommentsAdapter.OnItemClickListener {

    private RecyclerView recyclerListComments;
    private ArrayList<Comments> listComments;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference commentsReference;

    private CommentsAdapter adapter;

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    // TODO: Customize parameters
    public static ItemCommentsDetailDialogFragment newInstance(int itemCount) {
        final ItemCommentsDetailDialogFragment fragment = new ItemCommentsDetailDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
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

    private void getComments() {
        commentsReference = firebaseDatabase.getReference("comments");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshots = snapshot.getChildren();
                for(DataSnapshot data : snapshots) {
                    adapter.addComments(data.getValue(Comments.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        commentsReference.addValueEventListener(valueEventListener);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerListComments = view.findViewById(R.id.recyclerListComments);

        LinearLayoutManager acomodador = new LinearLayoutManager(getActivity());
        recyclerListComments.setLayoutManager(acomodador);

        listComments = new ArrayList<>();

        Comments comments1 = new Comments(1,"User1345", "2 hours ago",
                "You are very strong, thank you for sharing your story with us," +
                        " we are here for you.");
        Comments comments2 = new Comments(2, "User46807", "3hours ago",
                "I hope everything is better now");

        listComments.add(comments1);
        listComments.add(comments2);

        adapter = new CommentsAdapter(listComments, this);
        recyclerListComments.setAdapter(adapter);

        getComments();

    }


    @Override
    public void onItemClick(Comments comments) {

    }
}