package com.hrsoftbd.rz.restaurentsapp.retrofit;
import com.hrsoftbd.rz.restaurentsapp.DataModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by rzroky
 */

public interface ApiInterface {

    @GET("http://restaurant.mpapp.info/api/products")
    Call<List<DataModel>> getdata();



//    @POST("get_all_data")
//    @FormUrlEncoded
//    Call<List<DataModel>> get_all_data(@Field("last_update") String last_update);

}
