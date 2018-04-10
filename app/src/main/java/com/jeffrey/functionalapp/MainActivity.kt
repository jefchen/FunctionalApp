package com.jeffrey.functionalapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Implement code from https://tech.instacart.com/rxjava-state-the-basics-f842eaee7ee1
 */

class MainActivity : AppCompatActivity() {

    private var view: CommentFormView? = null
    private var presenter: CommentFormPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = CommentFormView(findViewById(R.id.comment_form_view))
        val service = CommentService()
        val model = CommentFormModel(service)
        val viewModel = CommentFormViewModel(model)
        presenter = CommentFormPresenter(viewModel)

        val mPresenter = presenter
        val mView = view
        if (mPresenter != null && mView != null) {
            mPresenter.attach(mView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }
}
