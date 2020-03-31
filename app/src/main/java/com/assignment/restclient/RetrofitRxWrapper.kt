package com.assignment.restclient

import android.content.Context
import com.assignment.model.ResponseData
import com.assignment.rxbase.BaseServiceRxWrapper
import io.reactivex.Single

class RetrofitRxWrapper(mContext: Context, apiEndPoint: String) : BaseServiceRxWrapper(mContext,apiEndPoint) {

    private val service: RetrofitService = this.retrofit.create(RetrofitService::class.java)

    fun fetchTradingRepository(): Single<ResponseData> {
        return this.service.fetchRepositories().cache()
    }



}
