package com.builderlandingpage.myapplication.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class MembershipPacakge(
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("package")
    @Expose
    val packageX: List<Package?>?,
    @SerializedName("status")
    @Expose
    val status: String?
) {
    @Keep
    data class Package(
        @SerializedName("balancesheet_id")
        @Expose
        val balancesheetId: String?,
        @SerializedName("is_active")
        @Expose
        val isActive: String?,
        @SerializedName("member_category_id")
        @Expose
        val memberCategoryId: String?,
        @SerializedName("no_of_months")
        @Expose
        val noOfMonths: String?,
        @SerializedName("package_amount")
        @Expose
        val packageAmount: String?,
        @SerializedName("package_id")
        @Expose
        val packageId: String?,
        @SerializedName("package_name")
        @Expose
        val packageName: String?
    )
}