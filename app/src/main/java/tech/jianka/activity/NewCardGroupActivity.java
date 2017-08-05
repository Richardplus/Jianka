package tech.jianka.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

import tech.jianka.data.Item;
import tech.jianka.fragment.TabsFragment;
import tech.jianka.fragment.TabsFragmentManager;

import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

public class NewCardGroupActivity extends AppCompatActivity {
    private EditText mEditGroupTitle;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card_group);

        setTitle(R.string.action_new_card_list);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mEditGroupTitle = (EditText) findViewById(R.id.new_card_group_title);
        mBtnSave = (Button) findViewById(R.id.new_card_group_save);

    }

    public void btnSave(View view) {
        String title = mEditGroupTitle.getText().toString().trim();
        if(!title.equals("")){
            TabsFragment fragment =
            TabsFragmentManager.getFragment(TabsFragmentManager.GROUP_FRAGMENT);
            fragment.adapter.addItem(new Item(title,getSpecifiedSDPath("jianka/data/" + title)));
            new File(getSpecifiedSDPath("jianka/data/" + title)).mkdirs();

            NavUtils.navigateUpFromSameTask(this);
        }
    }

//    public static void putString(String key, String value, Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences("pref_group", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(key, value);
//        editor.apply();
//    }
//
//    public static String getString(String key, Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences("pref_group", Context.MODE_PRIVATE);
//        return sharedPref.getString(key, "");
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
