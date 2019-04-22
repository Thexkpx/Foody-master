package foody.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterMenu extends RecyclerView.Adapter<RecyclerViewAdapterMenu.MyViewHolderOrder> {
    private Context mContext_order ;
    private List<order_class> mData_order ;

    public RecyclerViewAdapterMenu(Context mContext_order, List<order_class> mData_order) {
        this.mContext_order = mContext_order;
        this.mData_order = mData_order;

    }

    @NonNull
    @Override
    public RecyclerViewAdapterMenu.MyViewHolderOrder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext_order);
        view = mInflater.inflate(R.layout.cardview_item_order,viewGroup,false);
        return new RecyclerViewAdapterMenu.MyViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterMenu.MyViewHolderOrder holder, final int position) {
        holder.tv_menu_name_order.setText(mData_order.get(position).getMenu_name_order());
        holder.img_menu_thumbnail_order.setImageResource(mData_order.get(position).getThumbnail_order());
        holder.gia_menu_order.setText(mData_order.get(position).getGia_order());

    }

    @Override
    public int getItemCount() {
        return mData_order.size();
    }

    public static class MyViewHolderOrder extends RecyclerView.ViewHolder {

        TextView tv_menu_name_order;
        ImageView img_menu_thumbnail_order;
        TextView gia_menu_order;
        ImageButton btndelete_order;

        public MyViewHolderOrder(View itemView) {
            super(itemView);

            tv_menu_name_order = (TextView) itemView.findViewById(R.id.name_menu_order);
            img_menu_thumbnail_order = (ImageView) itemView.findViewById(R.id.image_menu_order);
            gia_menu_order = (TextView) itemView.findViewById(R.id.gia_menu_order);
            btndelete_order = (ImageButton) itemView.findViewById(R.id.btn_delete_menu_order);
        }
    }
}
