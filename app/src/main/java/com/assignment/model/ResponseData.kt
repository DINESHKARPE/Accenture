package com.assignment.model

import com.google.gson.annotations.SerializedName




class ResponseData  {

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("rows")
    var data: MutableList<Row>? = null



}



