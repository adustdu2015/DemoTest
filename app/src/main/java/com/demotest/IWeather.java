package com.demotest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a on 2017/8/26.
 */

public interface IWeather {
   @GET ("/v3/weather/now.json")
   Call<WeatherBean> weather( @Query ("key") String key, @Query("location") String location);
}
