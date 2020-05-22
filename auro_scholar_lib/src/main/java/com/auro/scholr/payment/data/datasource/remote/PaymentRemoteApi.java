package com.auro.scholr.payment.data.datasource.remote;

import com.auro.scholr.core.common.AppConstant;
import com.auro.scholr.core.network.URLConstant;
import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PaymentRemoteApi {


    @POST(URLConstant.DASHBOARD_API)
    @FormUrlEncoded
    Single<Response<JsonObject>> getDashboardData(@FieldMap Map<String, String> params);


}