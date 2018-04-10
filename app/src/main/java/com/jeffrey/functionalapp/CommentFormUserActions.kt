package com.jeffrey.functionalapp

// User modifies the comment text field
data class TextChangedEvent(val enteredText: String)

// User clicks submit
data class SubmitCommentEvent(val comment: String)