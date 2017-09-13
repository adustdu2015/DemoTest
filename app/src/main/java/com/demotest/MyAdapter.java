package com.demotest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by a on 2017/9/5.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private List<Fruit> mFruits;
   private Context mContext;
   @Override
   public MyAdapter.ViewHolder onCreateViewHolder( final ViewGroup parent, final int viewType ) {
	  if ( mContext ==null ){
		 mContext = parent.getContext();
	  }
	  View vView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item ,parent ,false);
	  final ViewHolder vViewHolder = new ViewHolder(vView);
	  vViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick( final View v ) {
			int position = vViewHolder.getAdapterPosition();
			Fruit mfruit = mFruits.get(position);
			Intent vIntent = new Intent(mContext ,Detail.class);
			vIntent.putExtra("1" , mfruit.getIconId());
			vIntent.putExtra("2" , mfruit.getDes());
			mContext.startActivity(vIntent);
		 }
	  });
	  return vViewHolder;
   }

   public MyAdapter( final List< Fruit > pFruits ) {
	  mFruits = pFruits;
   }

   @Override
   public void onBindViewHolder( final MyAdapter.ViewHolder holder, final int position ) {
			Fruit vFruit = mFruits.get(position);
	  		holder.mCircleImageView .setImageResource(vFruit.getIconId());
	  		holder.mTextView.setText(vFruit.getName());
	  		holder.mTextView2.setText(vFruit.getDes());
   }

   @Override
   public int getItemCount() {

	  return mFruits.size();
   }

   static class ViewHolder extends RecyclerView.ViewHolder {
	  CircleImageView mCircleImageView;
	  TextView mTextView , mTextView2;
	  CardView mCardView;
	  public ViewHolder( final View itemView ) {
		 super(itemView);
		 mCardView = ( CardView ) itemView;
		 mCircleImageView = ( CircleImageView ) itemView.findViewById(R.id.circleImage);
		 mTextView = ( TextView ) itemView.findViewById(R.id.item_title);
		 mTextView2 = ( TextView ) itemView.findViewById(R.id.descript_name);

	  }
   }
}
