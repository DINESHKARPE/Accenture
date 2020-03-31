package com.assignment.presenter

import android.util.Log
import com.assignment.delegate.MainActivityDelegate
import com.assignment.model.ResponseData
import com.assignment.restclient.RetrofitRxWrapper
import com.assignment.rxbase.BaseServiceRxWrapper
import com.assignment.viewmodel.MainActivityViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

internal class MainActivityPresenter(
    private val viewDelegate: MainActivityDelegate,
    private val wrapper: RetrofitRxWrapper
) {

    val viewModel: MainActivityViewModel by lazy {
        this.viewDelegate.getViewModel(MainActivityViewModel::class.java)
    }
    private var disposable: Disposable? = null

    fun getItems() {
        viewDelegate.hideErrorView()
        viewDelegate.hideRecyclerView()
        viewDelegate.showShimmerAnimation()

        if (viewModel.single == null) {
            viewModel.single = wrapper.fetchTradingRepository()
        }
        disposable?.dispose()

        disposable = this.viewModel.single?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<ResponseData>() {
                override fun onSuccess(response: ResponseData) {
                    viewDelegate.hideShimmerAnimation()
                    viewDelegate.showRecyclerView()
                    viewModel.response = response
                    viewModel.single = null
                    viewDelegate.onSuccess(viewModel.response!!)

                }

                override fun onError(e: Throwable) {
                    viewModel.single = null
                    viewDelegate.hideShimmerAnimation()
                    viewDelegate.showErrorView()
                    var message: String = if (e is UnknownHostException) {
                        BaseServiceRxWrapper.NO_INTERNET_CONNECTION
                    } else {
                        e.message.toString()
                    }
                    Log.d("TAG", message)
                    dispose()
                }
            })

    }






}
