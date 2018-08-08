package com.example.alexandr.trinitydigitaltest.ui.user

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexandr.trinitydigitaltest.App
import com.example.alexandr.trinitydigitaltest.R
import com.example.alexandr.trinitydigitaltest.data.UserModel
import com.example.alexandr.trinitydigitaltest.di.DaggerUserListComponent
import com.example.alexandr.trinitydigitaltest.di.UserListModule
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject


class UserListFragment : Fragment(), UserListView {

    private val adapter = UserAdapter()

    @Inject
    lateinit var presenter: UserListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var contentView = super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_user_list, container, false)
        }
        inject()
        return contentView
    }

    private fun inject() {
        val appComponent = (activity?.application as App).appComponent
        val component = DaggerUserListComponent.builder()
                .userListModule(UserListModule(activity!!, this))
                .appComponent(appComponent)
                .build()
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val attrs = intArrayOf(android.R.attr.listDivider)
        val a = context?.obtainStyledAttributes(attrs)
        val divider = a?.getDrawable(0)
        val inset = resources.getDimensionPixelSize(R.dimen.divider_margin)
        val insetDivider = InsetDrawable(divider, inset, 0, 0, 0)
        a?.recycle()

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(insetDivider)
        rvUserList.addItemDecoration(itemDecoration)
        rvUserList.adapter = adapter
        presenter.loadData()
    }

    override fun renderUserList(itemList: List<UserModel>) {
        rvUserList.visibility = View.VISIBLE
        adapter.updateItems(itemList)
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showError(message: String) {
        tvError.text = message
        tvError.visibility = View.VISIBLE
    }

    override fun hideError() {
        tvError.visibility = View.GONE
    }

    override fun context(): Context? {
        return context
    }
}
