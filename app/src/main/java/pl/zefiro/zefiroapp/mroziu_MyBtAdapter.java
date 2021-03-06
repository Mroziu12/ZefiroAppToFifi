package pl.zefiro.zefiroapp;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mroziu_MyBtAdapter extends RecyclerView.Adapter<mroziu_MyBtAdapter.MyViewHolder> {
    String TAG = "RecList";
    Context context;
    ArrayList<BluetoothDevice> names;
    private OnCardListener onCardListener;

    public mroziu_MyBtAdapter(Context ct, ArrayList<BluetoothDevice> name1, OnCardListener onCardListener){
        context=ct;
        names=name1;
        this.onCardListener=onCardListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Wywolano mnie");
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.mroziu_my_row,parent,false);

        return new MyViewHolder(view,onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        String nazwaUrzadzenia=names.get(position).getName();
        holder.myText.setText(nazwaUrzadzenia);
        Log.d(TAG, "onBindViewHolder: holder:"+holder);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: size:"+names.size());
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myText;
        OnCardListener onCardListener;

        public MyViewHolder(@NonNull View itemView,OnCardListener onCardListener) {
            super(itemView);
            myText = itemView.findViewById(R.id.tvdeviceName);
            this.onCardListener= onCardListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener{
        void onCardClick(int position);
    }
}
