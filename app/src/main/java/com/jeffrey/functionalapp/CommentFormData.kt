package com.jeffrey.functionalapp


data class CommentFormData(
        // defines what user has entered
        val comment: String,
        // defines if the entered comment is valid for submission
        val isCommentValid: Boolean,
        // defines if there is a submit request in progress
        val submitRequest: Lce<String>? = null
)
