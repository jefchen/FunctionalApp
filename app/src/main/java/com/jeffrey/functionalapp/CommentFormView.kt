package com.jeffrey.functionalapp

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

class CommentFormView(rootView: View) {

    // View binding logic
    private val commentTextField: EditText = rootView.findViewById(R.id.comment_text_field)
    private val submitButton: Button = rootView.findViewById(R.id.submit_button)

    private var textWatcher: TextWatcher? = null

    // Our view update function, takes a view state
    // snapshot and updates the android views
    fun setViewState(state: CommentFormViewState) {
        if (state.textEntered != commentTextField.text.toString()) {
            commentTextField.setText(state.textEntered)
        }
        submitButton.isEnabled = state.isSubmitButtonEnabled

        if (textWatcher != null) {
            commentTextField.removeTextChangedListener(textWatcher)
        }
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                state.onTextEntered(s.toString())
            }
        }
        commentTextField.addTextChangedListener(textWatcher)

        submitButton.setOnClickListener {
            state.onSubmitClicked()
        }
    }
}
