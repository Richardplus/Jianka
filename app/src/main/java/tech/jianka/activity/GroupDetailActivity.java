package tech.jianka.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import tech.jianka.adapter.CardAdapter;
import tech.jianka.adapter.TaskAdapter;
import tech.jianka.data.Card;
import tech.jianka.data.DataType;

import static tech.jianka.utils.ItemUtils.getAllSubCards;

public class GroupDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private String mGroupPath;
    private List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra(DataType.ACTIVITY_TITLE));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mGroupPath = intent.getStringExtra(DataType.GROUP_PATH);

        cards = getAllSubCards(mGroupPath);
        switch (intent.getIntExtra(DataType.GROUP_TYPE, 999)) {
            case DataType.CARD:
                initAdapterForCards(this);
                break;
            case DataType.TASK:
                initAdapterForTask(this);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.detail_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initAdapterForTask(final Activity activity) {
        mAdapter = new TaskAdapter(cards, mRecyclerView, new TaskAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int clickedCardIndex) {

                if (clickedCardIndex < 4) {

                } else {
                    Intent intent = new Intent(activity, NewCardActivity.class);
                    intent.putExtra(DataType.INIT_TYPE, DataType.EDIT_TASK);
                    intent.putExtra(DataType.TASK_INDEX, clickedCardIndex);
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(final int clickedCardIndex) {
                String[] options = activity.getResources().getStringArray(R.array.task_group_options);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                AlertDialog alertDialog = builder.setTitle(getString(R.string.choose_operation))
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        break;
                                    case 1:
                                        // TODO: 2017/8/6 rename
                                        break;
                                    case 2:
                                        // TODO: 2017/8/6 修改封面
                                        break;
                                }
                            }
                        }).create();
                alertDialog.show();
            }

            @Override
            public void onTaskCheck(int clickedPosition) {
                ((TaskAdapter) mAdapter).removeItem(clickedPosition);
            }
        });
    }

    private void initAdapterForCards(final Activity activity) {
        mAdapter = new CardAdapter(cards, mRecyclerView, new CardAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int clickedCardIndex) {
                // TODO: 2017/7/26 处理卡片单击事件
                Intent intent = new Intent(activity, NewCardActivity.class);
                intent.putExtra(DataType.INIT_TYPE, DataType.EDIT_CARD);
                intent.putExtra(DataType.CARD_INDEX, clickedCardIndex);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(final int clickedCardIndex) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                String[] options;
                options = activity.getResources().getStringArray(R.array.card_options);
                AlertDialog alertDialog = builder.setTitle("选择操作")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        ((CardAdapter) mAdapter).shareItem(clickedCardIndex, activity);
                                        break;
                                    case 1:
                                        if (!((CardAdapter) mAdapter).removeItem(clickedCardIndex)) {
                                            Toast.makeText(activity, "删除失败", Toast.LENGTH_LONG).show();
                                        }

                                        break;
                                    case 2:
                                        // TODO: 2017/8/6 切换分组功能
                                        break;
                                }
                            }
                        }).create();
                alertDialog.show();
            }
        });
    }

}
