package com.example.paginglibrarywithmvvm.users.dtos

import com.google.gson.annotations.SerializedName

class User {
    @field:SerializedName("avatar")
    var avatar: String? = null
    @field:SerializedName("email")
    var email: String? = null
    @field:SerializedName("first_name")
    var firstName: String? = null
    @field:SerializedName("id")
    var id: Long? = null
    @field:SerializedName("last_name")
    var lastName: String? = null
}