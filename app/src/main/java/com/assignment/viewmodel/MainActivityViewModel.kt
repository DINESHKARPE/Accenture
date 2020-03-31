package com.assignment.viewmodel


import androidx.lifecycle.ViewModel
import com.assignment.model.ResponseData
import io.reactivex.Single

class MainActivityViewModel : ViewModel() {

    var single: Single<ResponseData>? = null
    var response: ResponseData? = null
}
