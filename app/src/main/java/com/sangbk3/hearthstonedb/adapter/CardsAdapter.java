package com.sangbk3.hearthstonedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sangbk3.hearthstonedb.R;
import com.sangbk3.hearthstonedb.model.Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> implements Filterable {

    private List<Card> cards;
    private List<Card> filteredCards;

    private int rowLayout;

    private Context context;

    public CardsAdapter(List<Card> cards, List<Card> filteredCards, int rowLayout, Context context) {
        this.cards = cards;
        this.filteredCards = filteredCards;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public static class CardsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardLayout;
        TextView nameView;
        TextView typeView;
        TextView playerClassView;
        ImageView cardImageView;

        public CardsViewHolder(View view) {
            super(view);

            cardLayout = view.findViewById(R.id.cards_layout);
            nameView = view.findViewById(R.id.name);
            typeView = view.findViewById(R.id.type);
            playerClassView = view.findViewById(R.id.player_class);
            cardImageView = view.findViewById(R.id.card_image);
        }
    }

    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
        Card card = filteredCards.get(position);

        String imageUrl = "https://vignette.wikia.nocookie.net/hearthstone/images/c/c4/Card_back-Default.png/revision/latest/scale-to-width-down/322?cb=20140823204025";

        if (card.getImg() != null) {
            imageUrl = card.getImg();
        }

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.hs_cardback)
                .error(R.drawable.hs_cardback)
                .into(holder.cardImageView);

        holder.nameView.setText(card.getName());
        holder.typeView.setText(card.getType());
        holder.playerClassView.setText(card.getPlayerClass());
    }

    @Override
    public int getItemCount() {
        return filteredCards.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final String charString = constraint.toString();

                if (charString.isEmpty()) {
                    filteredCards = cards;
                } else {
                    List<Card> newFilteredCards = new ArrayList<>();
                    for (Card c : cards) {
                        if (c.getName().toLowerCase().contains(charString.toLowerCase())) {
                            newFilteredCards.add(c);
                        }
                    }

                    filteredCards = newFilteredCards;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterResults;

                Log.d("Sangbug", "filter: changed data for cards " + filteredCards.size());

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d("Sangbug", "publishResults: publishing data changed");
                notifyDataSetChanged();
            }
        };
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        this.filteredCards = cards;
    }
}
