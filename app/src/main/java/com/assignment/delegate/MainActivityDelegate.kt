package com.assignment.delegate

import androidx.lifecycle.ViewModel
import com.assignment.model.ResponseData

internal interface MainActivityDelegate {

    fun showShimmerAnimation()
    fun hideShimmerAnimation()

    fun showErrorView()
    fun hideErrorView()
    fun isConnectedToInternet(): Boolean
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T
    fun onSuccess(response: ResponseData)
    fun hideRecyclerView()
    fun showRecyclerView()
}
