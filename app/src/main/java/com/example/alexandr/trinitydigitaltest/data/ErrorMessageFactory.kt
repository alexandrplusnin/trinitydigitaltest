package com.example.alexandr.trinitydigitaltest.data

import android.content.Context
import com.example.alexandr.trinitydigitaltest.R
import java.net.SocketException
import java.net.UnknownHostException

/**
 * @param context   Context
 * @param exception исключение, по которому нужно сгенерировать текст
 *
 * @return [String] сообщение об ошибке
 */
fun generateErrorMessage(context: Context, exception: Throwable): String {
    return if (exception is UnknownHostException || exception is SocketException) {
        context.getString(R.string.error_offline_mode)
    } else if (exception is UnsuccessfulRequestException) {
        context.getString(R.string.unsuccessful_response, exception.code)
    } else {
        context.getString(R.string.network_error_unknown)
    }
}