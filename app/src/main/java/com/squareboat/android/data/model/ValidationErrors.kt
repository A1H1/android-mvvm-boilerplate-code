package com.squareboat.android.data.model

import com.google.gson.annotations.SerializedName

data class ValidationErrors(
    @SerializedName("sample_error")
    var sampleErrorArray: List<String> = emptyList(),
) {
    val sampleError: String get() = sampleErrorArray[0]
}