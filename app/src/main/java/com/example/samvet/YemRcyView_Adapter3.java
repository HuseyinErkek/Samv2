package com.example.samvet;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class YemRcyView_Adapter3 extends RecyclerView.Adapter<YemRcyView_Adapter3.MyViewHolder> {
    private Context context;
    private ArrayList<YemModel> yemModels;
    private HashMap<String, Double> sabitler;
    private double toplamSonuc = 0.0;

    public YemRcyView_Adapter3(Context context, ArrayList<YemModel> yemModels) {
        this.context = context;
        this.yemModels = yemModels;
        this.sabitler = new HashMap<>();
        sabitler.put("Mısır", 3350.0);
        sabitler.put("Arpa", 2640.0);
        sabitler.put("Buğday", 3163.0);
        sabitler.put("Soya Küspesi", 2230.0);
        sabitler.put("Tam Yağlı Soya", 3300.0);
        sabitler.put("Ayçiçeği Küspesi", 1543.0);
        sabitler.put("Bitkisel Yağ", 9000.0);
        sabitler.put("Mısır Gluteni", 3720.0);
        sabitler.put("Kepek", 1300.0);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        YemModel yemModel = yemModels.get(position);
        holder.textView.setText(yemModel.getYemadi());
        holder.imageView.setImageResource(yemModel.getImage());

        holder.editText.removeTextChangedListener(holder.textWatcher);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.toString().isEmpty()) {
                        // Boş bir dize olduğunda sonucu boş olarak ayarla
                        holder.result.setText("");
                    } else {
                        double girilenOran = Double.parseDouble(s.toString());
                        double sabit = sabitler.get(yemModel.getYemadi());
                        double sonuc = (girilenOran * sabit) / 100;
                        holder.result.setText(String.valueOf(sonuc));
                        if (toplamSonuc<2262){
                            toplamSonuc += sonuc; // Sonucu toplamSonuc'a ekleyin
                        }
                        else {
                            Toast.makeText(context, "Hata: Değer 3182 aşamaz", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        holder.textWatcher = textWatcher;
        holder.editText.addTextChangedListener(textWatcher);
    }

    @Override
    public int getItemCount() {
        return yemModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        EditText editText;
        TextView result;
        TextWatcher textWatcher;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.oran);
            imageView = itemView.findViewById(R.id.gorsel);
            textView = itemView.findViewById(R.id.yemname);
            result = itemView.findViewById(R.id.sonuc);
            textWatcher = null;
        }
    }

    public double getToplamSonuc() {
        return toplamSonuc;
    }
}
