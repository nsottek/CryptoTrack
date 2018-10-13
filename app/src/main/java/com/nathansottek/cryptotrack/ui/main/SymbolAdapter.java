package com.nathansottek.cryptotrack.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nathansottek.cryptotrack.R;

import java.util.List;

public class SymbolAdapter extends RecyclerView.Adapter {

  private SymbolAdapterCallback callback;
  private List<String> symbols;
  private int selectedPosition;

  SymbolAdapter(SymbolAdapterCallback callback, List<String> symbols) {
    this.callback = callback;
    this.symbols = symbols;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_symbol_item, viewGroup, false);
    return new SymbolViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    ((SymbolViewHolder) viewHolder).bind(symbols.get(getDataPosition(i)));
  }

  @Override
  public int getItemCount() {
    return Integer.MAX_VALUE;
  }

  int getDataPosition(int adapterPosition) {
    return adapterPosition % symbols.size();
  }

  int getStartingPosition() {
    int pos = Integer.MAX_VALUE / 2;
    while (pos % symbols.size() != 1) {
      pos++;
    }
    return pos;
  }

  void setSelectedPosition(int selectedPosition) {
    this.selectedPosition = selectedPosition;
  }

  class SymbolViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.symbol_value) TextView symbolValue;

    SymbolViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.symbol_value)
    void onSymbolClicked() {
      int position = getAdapterPosition() < selectedPosition ? selectedPosition - 2 : selectedPosition + 2;
      callback.scrollToPosition(position);
    }

    void bind(String symbol) {
      symbolValue.setText(symbol);
    }
  }
}
