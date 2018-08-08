package com.example.alexandr.trinitydigitaltest.ui

/**
 * Интерфейс, представляющий собой Presenter в MVP
 */
abstract class Presenter<T>(var view: T?) where T : LoadDataView {
    /**
     * Метод, контролирующий жизненный цикл View. Он должен вызываться в методе onResume()
     * (Activity или Fragment).
     */
    open fun resume() {
    }
    /**
     * Метод, контролирующий жизненный цикл View. Он должен вызываться в методе onDestroy()
     * (Activity или Fragment).
     */
    open fun destroy() {
        view?.hideLoading()
        view = null
    }
}
