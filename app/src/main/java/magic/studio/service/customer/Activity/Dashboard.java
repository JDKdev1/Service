package magic.studio.service.customer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import magic.studio.service.customer.Adapter.ServiceListAdapter;
import magic.studio.service.customer.Model.ServiceModel;
import magic.studio.service.customer.R;

public class Dashboard extends AppCompatActivity {

    List<ServiceModel> serviceModelList;
    RecyclerView home_service_list;
    ServiceListAdapter serviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Datas();
        init();
    }

    private void init() {
        home_service_list = (RecyclerView) findViewById(R.id.home_service_list);

        serviceListAdapter = new ServiceListAdapter(this, serviceModelList);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        home_service_list.setLayoutManager(layoutManager);
        home_service_list.setAdapter(serviceListAdapter);

        serviceListAdapter.setOnClickListen(new ServiceListAdapter.AddTouchListen() {
            @Override
            public void onClick(int position) {
                Intent intent = null;
                if (serviceModelList.get(position).getName().equals("Booking")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("History")){
                    intent = new Intent(Dashboard.this, BookingHistory.class);
                }else if (serviceModelList.get(position).getName().equals("Map")){
                    intent = new Intent(Dashboard.this, BookingMap.class);
                }else if (serviceModelList.get(position).getName().equals("Contacts")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("Profile")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("Settings")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("About")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("Help")){
                    intent = new Intent(Dashboard.this, BookingService.class);
                }else if (serviceModelList.get(position).getName().equals("Exit")){
                    finish();
                    System.exit(0);
                }
                startActivity(intent);
            }
        });

    }

    private void Datas() {
        serviceModelList = new ArrayList<>();

        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName("Booking");
        serviceModel.setImage(R.drawable.service_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("History");
        serviceModel.setImage(R.drawable.history_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Map");
        serviceModel.setImage(R.drawable.map_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Contacts");
        serviceModel.setImage(R.drawable.contact_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Profile");
        serviceModel.setImage(R.drawable.profile_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Settings");
        serviceModel.setImage(R.drawable.setting_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("About");
        serviceModel.setImage(R.drawable.info_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Help");
        serviceModel.setImage(R.drawable.info_icon);
        serviceModelList.add(serviceModel);

        serviceModel = new ServiceModel();
        serviceModel.setName("Exit");
        serviceModel.setImage(R.drawable.exit_icon);
        serviceModelList.add(serviceModel);
    }

}
