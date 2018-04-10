package com.jeffrey.functionalapp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CommentFormPresenter(private val viewModel: CommentFormViewModel) {

    private var disposable: Disposable? = null

    fun attach(view: CommentFormView) {
        disposable = viewModel
                .viewStateStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { state ->
                    view.setViewState(state)
                }
    }

    fun detach() {
        disposable?.dispose()
    }
}