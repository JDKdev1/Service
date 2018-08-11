package magic.studio.service.customer.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import magic.studio.service.customer.Fragment.BookingOrderFragment;
import magic.studio.service.customer.Fragment.DeliveryOrderFragment;
import magic.studio.service.customer.R;

public class BookingService extends AppCompatActivity {

    Toolbar mToolbar;
    FrameLayout booking_tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_service);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView title = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        title.setText("Booking Service");

        init();
    }

    private void init() {
        booking_tabs = (FrameLayout) findViewById(R.id.booking_tabs);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = new BookingOrderFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(booking_tabs.getId(), fragment, "Booking");
                fragmentTransaction.commit();
            }
        });
    }
}
