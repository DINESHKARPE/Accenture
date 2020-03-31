package com.assignment.model

import com.google.gson.annotations.SerializedName




class Row  {

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("description")
    var description: String? = ""

    @SerializedName("imageHref")
    var imageHref: String? = ""

}



