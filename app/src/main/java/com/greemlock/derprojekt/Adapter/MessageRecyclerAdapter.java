package com.greemlock.derprojekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.greemlock.derprojekt.Objects.Message;
import com.greemlock.derprojekt.Objects.User;
import com.greemlock.derprojekt.R;

import java.util.List;


public class MessageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Message> messages;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public MessageRecyclerAdapter(List<Message> messages){
        this.messages = messages;
    }

    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView message;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;

        public ViewHolder0(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView message;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;

        public ViewHolder1(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_layout_recycler,parent,false);
                return new ViewHolder0(itemView);
            case 1:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_layout_recycler_my_messages,parent,false);
                return new ViewHolder1(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Message objectMessage = messages.get(position);

        switch (holder.getItemViewType()) {
            case 0:

                ViewHolder0 viewHolder0 = (ViewHolder0)holder;

                Query QueryGetName = FirebaseDatabase.getInstance().getReference("users").orderByChild("userUID").equalTo(objectMessage.getMessage_sender_uid());
                QueryGetName.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            User user = dataSnapshot.getValue(User.class);
                            assert user != null;
                            viewHolder0.name.setText(user.getUserName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                viewHolder0.message.setText(objectMessage.getMessage());

                break;

            case 1:
                ViewHolder1 viewHolder1 = (ViewHolder1)holder;
                viewHolder1.name.setText(user.getDisplayName());
                viewHolder1.message.setText(objectMessage.getMessage());

        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        final Message objectMessage = messages.get(position);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String userUid = user.getUid();
        if(userUid.equals(objectMessage.getMessage_sender_uid())){
            return 1;
        }
        else{
            return 0;
        }
    }

}
