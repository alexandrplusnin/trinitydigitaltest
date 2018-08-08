package com.example.alexandr.trinitydigitaltest.ui.user

import com.example.alexandr.trinitydigitaltest.data.ApiService
import com.example.alexandr.trinitydigitaltest.data.generateErrorMessage
import com.example.alexandr.trinitydigitaltest.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserListPresenter(
        view: UserListView?,
        private var apiService: ApiService
) : Presenter<UserListView>(view) {

    fun loadData() {
        apiService.getUserList().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it.body()?.let { it1 -> view?.renderUserList(it1) }
                        },
                        {
                            view?.hideLoading()
                            view?.context()?.let { it1 -> generateErrorMessage(it1, it) }?.let { it2 ->
                                view?.showError(it2)
                            }
                        },
                        {
                            view?.hideLoading()
                        },
                        {
                            view?.showLoading()
                        }
                )
    }
}
