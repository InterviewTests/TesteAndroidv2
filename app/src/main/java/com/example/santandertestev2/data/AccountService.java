package com.example.santandertestev2.data;

import com.example.santandertestev2.domain.InvoiceItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountService {

    @GET("/photos")
    Call<List<InvoiceItem>> getInvoice();

}
