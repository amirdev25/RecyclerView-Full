package uz.amirdev.rvfull;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> userList = new ArrayList<>();
    RVAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        loadData();
        EditText userName = findViewById(R.id.userName);
        EditText userPhone = findViewById(R.id.userPhone);

        Button button = findViewById(R.id.addBtn);
        button.setOnClickListener(view -> {
            if (userList.size()<=20){
                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();
                userList.add(new User(name, phone));
                adapter.refreshData(userList);
                userName.setText("");
                userPhone.setText("");
            }
        });
    }

    private void loadData() {
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        userList.add(new User("Elbekjon", "+998908985879"));
        adapter.refreshData(userList);
    }

    private void initRecyclerView() {
        adapter = new RVAdapter(position -> {
            userList.remove(position);
            adapter.deleteItem(position);
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int startPosition = viewHolder.getAdapterPosition();
            int endPosition = target.getAdapterPosition();

            Collections.swap(userList, startPosition, endPosition);
            adapter.itemMoved(startPosition, endPosition, userList);
            return true;
        }


        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    User user = userList.get(viewHolder.getLayoutPosition());
                    int position = viewHolder.getLayoutPosition();
                    userList.remove(user);
                    adapter.deleteItem(position);
                    //Toast.makeText(MainActivity.this, "Element o'chirildi", Toast.LENGTH_SHORT).show();
                    Snackbar.make(recyclerView, "Element o'chirildi!", Snackbar.LENGTH_LONG)
                            .setAction("Bekor qilish", view -> {
                                userList.add(position, user);
                                adapter.refreshData(userList);
                            }).show();
            }
        }
    };

}