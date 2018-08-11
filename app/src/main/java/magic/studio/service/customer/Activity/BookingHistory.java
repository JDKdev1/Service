package magic.studio.service.customer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import magic.studio.service.customer.R;

public class BookingHistory extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView history_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView title = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        title.setText("History");

        init();
    }

    private void init() {
        history_list = (RecyclerView) findViewById(R.id.history_list);

        getHistoryAPI();
    }

    private void getHistoryAPI() {

    }
}
