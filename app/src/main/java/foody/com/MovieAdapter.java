package foody.com;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ankit on 27/10/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<movie> list;

    public MovieAdapter(Context context, List<movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_item_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        movie movie = list.get(position);

        holder.textTitle.setText(movie.getTen_menu());
        holder.textRating.setText(String.valueOf(movie.getSoluong()));
        holder.textYear.setText(String.valueOf(movie.getGiamenu()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textRating, textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.name_menu_order);
            textRating = itemView.findViewById(R.id.txt_sl);
            textYear = itemView.findViewById(R.id.gia_menu_order);
        }
    }

}