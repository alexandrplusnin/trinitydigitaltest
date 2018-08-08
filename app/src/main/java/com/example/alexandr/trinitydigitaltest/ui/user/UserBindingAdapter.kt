package com.example.alexandr.trinitydigitaltest.ui.user

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.format.DateUtils
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.alexandr.trinitydigitaltest.R
import com.example.alexandr.trinitydigitaltest.data.UserStatus
import com.example.alexandr.trinitydigitaltest.ui.StrokeCircleTransformation
import com.squareup.picasso.Picasso
import java.util.*

@BindingAdapter("user:avatar")
fun bindImage(view: ImageView, avatar: String) {
    Picasso.with(view.context)
            .load(avatar)
            .centerInside()
            .fit()
            .transform(StrokeCircleTransformation())
            .into(view)
}

@BindingAdapter("user:status")
fun bindStatus(view: ImageView, status: String) {
    when (status) {
        UserStatus.DND.title -> {
            view.setImageResource(R.drawable.red_circle)
        }
        UserStatus.AWAY.title -> {
            view.setImageResource(R.drawable.orange_circle)
        }
        UserStatus.ONLINE.title -> {
            view.setImageResource(R.drawable.green_circle)
        }
    }
}

@BindingAdapter("user:age", "user:similarity")
fun bindAge(view: TextView, age: Int, similarity: Int) {
    val raw = String.format("%s лет, %s%%", age, similarity)
    val ss = SpannableString(raw)
    var colorize = ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.green))
    if (similarity < 40) {
        colorize = ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.red))
    } else if (similarity < 70) {
        colorize = ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.orange))
    }
    ss.setSpan(colorize, age.toString().length + 5, raw.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
    view.text = ss
}

@BindingAdapter("user:lastSeen")
fun bindDate(view: TextView, lastSeen: Date) {
    view.text = DateUtils.getRelativeDateTimeString(
            view.context,
            lastSeen.time,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.DAY_IN_MILLIS * 2,
            DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_SHOW_DATE
    )
}

@BindingAdapter("user:unreadMessages")
fun bindUnreadMessages(view: TextView, unreadMessages: Int) {
    if (unreadMessages > 0) {
        view.visibility = View.VISIBLE
        view.text = unreadMessages.toString()
    } else {
        view.visibility = View.GONE
    }
}