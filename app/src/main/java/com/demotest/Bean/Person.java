package com.demotest.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a on 2017/9/10.
 */

public class Person implements Parcelable {
   public String name;
   public String gender;
   public int age;

   @Override
   public int describeContents() {
	  return 0;
   }

   @Override
   public void writeToParcel( Parcel dest, int flags ) {
	  dest.writeString(this.name);
	  dest.writeString(this.gender);
	  dest.writeInt(this.age);
   }

   public Person( final String pName, final String pGender, final int pAge ) {
	  name = pName;
	  gender = pGender;
	  age = pAge;
   }

   public String getName() {
	  return name;
   }

   public void setName( final String pName ) {
	  name = pName;
   }

   public String getGender() {
	  return gender;
   }

   public void setGender( final String pGender ) {
	  gender = pGender;
   }

   public int getAge() {
	  return age;
   }

   public void setAge( final int pAge ) {
	  age = pAge;
   }

   public Person() {
   }

   protected Person( Parcel in ) {
	  this.name = in.readString();
	  this.gender = in.readString();
	  this.age = in.readInt();
   }

   public static final Parcelable.Creator< Person > CREATOR = new Parcelable.Creator< Person >() {
	  @Override
	  public Person createFromParcel( Parcel source ) {
		 return new Person(source);
	  }

	  @Override
	  public Person[] newArray( int size ) {
		 return new Person[ size ];
	  }
   };
}
