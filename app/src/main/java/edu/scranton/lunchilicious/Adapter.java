package edu.scranton.lunchilicious;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<MenuItem> mList;
    private OnItemClick mListener;
    public interface OnItemClick{
        void OnItemClick(int position);
    }
    public void OnItemClick(OnItemClick listener){
        mListener=listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public int mID;
        public TextView mType;
        public TextView mName;
        public TextView mUnitPrice;
        DecimalFormat df = new DecimalFormat("#.##");
        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.foodNameTV);
            mType=itemView.findViewById(R.id.foodTypeTV);
            mUnitPrice=itemView.findViewById(R.id.priceTV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener !=null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public Adapter(Activity main, List<MenuItem> list) {
        this.mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items,parent,false);
        ViewHolder vH= new ViewHolder(v);
         return vH;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    MenuItem currentItem = mList.get(position);
    holder.mName.setText(currentItem.getFoodName());
    holder.mType.setText(currentItem.getFoodType());
    holder.mUnitPrice.setText(currentItem.getPrice());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        this.mList = menuItems;
        notifyDataSetChanged();
    }
}