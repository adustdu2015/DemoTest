package com.demotest;

/**
 * Created by a on 2017/9/5.
 */

public class Fruit {
   private int iconId;
	private String name ;
   private String des;
   public Fruit( final int pIconId, final String pName, final String pDes ) {
	  iconId = pIconId;
	  name = pName;
	  des = pDes;
   }

   public int getIconId() {
	  return iconId;
   }

   public void setIconId( final int pIconId ) {
	  iconId = pIconId;
   }

   public String getName() {
	  return name;
   }

   public void setName( final String pName ) {
	  name = pName;
   }

   public String getDes() {
	  return des;
   }

   public void setDes( final String pDes ) {
	  des = pDes;
   }


}
