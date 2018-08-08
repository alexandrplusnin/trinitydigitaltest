package com.example.alexandr.trinitydigitaltest.ui.user

import com.example.alexandr.trinitydigitaltest.data.UserModel
import com.example.alexandr.trinitydigitaltest.ui.LoadDataView

interface UserListView : LoadDataView {
    fun renderUserList(itemList: List<UserModel>)
}
