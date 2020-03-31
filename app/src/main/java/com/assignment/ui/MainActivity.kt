package com.assignment.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.BuildConfig
import com.assignment.R
import com.assignment.StandardActivity
import com.assignment.delegate.MainActivityDelegate
import com.assignment.presenter.MainActivityPresenter
import com.assignment.restclient.RetrofitRxWrapper
import com.assignment.viewmodel.MainActivityViewModel
import com.assignment.adapter.MainActivityListAdapter
import com.assignment.model.ResponseData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.error_layout.*

class MainActivity : StandardActivity(),
    MainActivityDelegate {

    private var mainActivityListAdapter: MainActivityListAdapter? = null
    private lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private var viewModel: MainActivityViewModel? = null

    private var repositoryList: ResponseData? = null

    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private val mockUrl by lazy {
        intent?.extras?.getString("MOCK_URL", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        var retrofitRxWrapper =
            RetrofitRxWrapper(applicationContext, mockUrl ?: BuildConfig.REST_API)

        this.mainActivityPresenter =
            MainActivityPresenter(
                this,
                retrofitRxWrapper
            )

        this.viewModel = this.getViewModel(MainActivityViewModel::class.java)


        retry.setOnClickListener {
            this.mainActivityPresenter.getItems()
        }

        repository_list.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 0)
        )

        swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)


        swipeRefreshLayout!!.setOnRefreshListener {
            this.mainActivityPresenter.getItems()
        }

    }



    override fun onResume() {
        super.onResume()
        this.mainActivityPresenter.getItems()
    }

    override fun showShimmerAnimation() {
        shimmer_view_container.visibility = View.VISIBLE
    }

    override fun hideShimmerAnimation() {
        shimmer_view_container.visibility = View.GONE
    }

    override fun showErrorView() {
        error_layout.visibility = View.VISIBLE
    }

    override fun hideErrorView() {
        error_layout.visibility = View.GONE
    }

    override fun onSuccess(response: ResponseData) {

        swipeRefreshLayout!!.isRefreshing = false

        this.repositoryList = response

        toolbar_title.text = response.title

        this.mainActivityListAdapter =
            MainActivityListAdapter(response.data, this)

        this.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        repository_list.layoutManager = this.layoutManager
        repository_list.adapter = mainActivityListAdapter
    }

    override fun hideRecyclerView() {
        repository_list.visibility = View.GONE
    }

    override fun showRecyclerView() {
        repository_list.visibility = View.VISIBLE
    }
}
