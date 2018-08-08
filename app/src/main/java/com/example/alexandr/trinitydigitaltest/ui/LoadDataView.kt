package com.example.alexandr.trinitydigitaltest.ui

import android.content.Context

/**
 * Интерфейс, представляющий View, которая будет загружать данные
 */
interface LoadDataView {

    /**
     * Показать прогресс-бар
     */
    fun showLoading()

    /**
     * Скрыть прогресс-бар
     */
    fun hideLoading()

    /**
     * Отображает сообщение об ошибке
     *
     * @param message сообщение
     */
    fun showError(message: String)

    fun hideError()

    /**
     * @return [Context].
     */
    fun context(): Context?

}