package magic.studio.service.customer.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import magic.studio.service.customer.Activity.Dashboard;
import magic.studio.service.customer.Model.ServiceModel;
import magic.studio.service.customer.R;

/**
 * Created by Android on 1/14/2018.
 */

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder>{
    Context context;
    List<ServiceModel> serviceModelList;
    AddTouchListen addTouchListen;

    public interface AddTouchListen {
        public void onClick(int position);
    }

    public void setOnClickListen(AddTouchListen addTouchListen) {
        this.addTouchListen = addTouchListen;
    }

    public ServiceListAdapter(Context context, List<ServiceModel> serviceModelList) {
        this.context = context;
        this.serviceModelList = serviceModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.service_list_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.service_name_TextView.setText(serviceModelList.get(position).getName());
        holder.service_ImageView.setBackgroundResource(serviceModelList.get(position).getImage());

        holder.service_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addTouchListen!=null){
                    addTouchListen.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView service_ImageView;
        TextView service_name_TextView;
        CardView service_card;

        public ViewHolder(View itemView) {
            super(itemView);
            service_card = (CardView) itemView.findViewById(R.id.service_card);
            service_ImageView = (ImageView) itemView.findViewById(R.id.service_ImageView);
            service_name_TextView = (TextView) itemView.findViewById(R.id.service_name_TextView);
        }
    }
}
