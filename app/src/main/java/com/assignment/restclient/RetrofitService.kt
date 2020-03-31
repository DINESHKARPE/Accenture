package com.assignment.restclient

import com.assignment.model.ResponseData
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {
    @GET("repositories")
    fun fetchRepositories(): Single<ResponseData>
}