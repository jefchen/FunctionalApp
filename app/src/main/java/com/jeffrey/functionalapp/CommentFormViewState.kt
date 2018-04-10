package com.jeffrey.functionalapp

data class CommentFormViewState(
        val textEntered: String,
        val isSubmitButtonEnabled: Boolean,
        val onTextEntered: (String) -> Unit, /* Callback when user modifies the text */
        val onSubmitClicked: () -> Unit  /* Callback when user clicks submit button */)
