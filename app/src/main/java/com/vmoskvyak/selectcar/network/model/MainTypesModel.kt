package com.vmoskvyak.selectcar.network.model

import com.google.gson.annotations.SerializedName
import com.vmoskvyak.selectcar.BuildConfig

class MainTypesModel {

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("pageSize")
    var pageSize: Int = 0

    @SerializedName("totalPageCount")
    var totalPageCount: Int = 0

    @SerializedName(BuildConfig.DATA_KEY)
    var mainTypesMap: Map<String, String> = HashMap()

}
