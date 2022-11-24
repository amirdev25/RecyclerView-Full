package uz.amirdev.rvfull;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH> {

    ArrayList<User> userList = new ArrayList<>();
    OnItemClickListener listener;

    public RVAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("Recycle")
        public void onBind(User user) {
            TextView index, name, phone;
            index = itemView.findViewById(R.id.userIndex);
            name = itemView.findViewById(R.id.userName);
            phone = itemView.findViewById(R.id.userPhone);

            index.setText("" + (getLayoutPosition() + 1));
            name.setText(user.getName());
            phone.setText(user.getPhone());
            Animation moveAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.move_animation);
            itemView.startAnimation(moveAnimation);
        }
    }

    interface OnItemClickListener {
        void onItemLongClickListener(int position);
    }

    @SuppressLint("NotifyDataSetChanged")
    void refreshData(ArrayList<User> data) {
        userList.clear();
        userList.addAll(data);
        notifyDataSetChanged();
    }

    void deleteItem(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
    }

    void itemMoved(int startPosition, int endPosition, ArrayList<User> userList) {
        notifyItemMoved(startPosition, endPosition);
        this.userList.clear();
        this.userList.addAll(userList);
    }

}
