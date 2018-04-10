package com.jeffrey.functionalapp

import io.reactivex.Observable

/**
 * Wish
 * Created by Jeffrey Chen on 4/9/18.
 * Copyright (c) 2017 ContextLogic. All rights reserved.
 */

class CommentFormModel(private val commentService: CommentService) : CommentFormModelContract {

    private fun reduce(event: TextChangedEvent): (CommentFormData) -> CommentFormData {
        // implementation
        return { state ->
            // Let's check comment validity
            val isValid = event.enteredText.length > 5
            // Update the current state with the changes
            state.copy(comment = event.enteredText, isCommentValid = isValid)
        }
    }

    private fun reduce(event: Lce<String>): (CommentFormData) -> CommentFormData {
        // Update the submit request
        return { state -> state.copy(submitRequest = event) }
    }

    override fun createDataStream(
            textChangedEvents: Observable<TextChangedEvent>,
            submitCommentEvents: Observable<SubmitCommentEvent>
    ): Observable<CommentFormData> {
        val textChangedReducer: Observable<Reducer<CommentFormData>> = textChangedEvents.map { textEvent -> reduce(textEvent) }

        // Explicitly declaring the type, for clarity sake
        val requestStateReducer: Observable<Reducer<CommentFormData>> = submitCommentEvents
                .switchMap { event -> commentService.submit(event.comment) }
                .map { lceEvent -> reduce(lceEvent) }

        // We now have a clear common type (which happens to be a lambda)
        val reducers: Observable<Reducer<CommentFormData>> = Observable.merge(requestStateReducer, textChangedReducer)

        // And to finish this
        val initialState = CommentFormData("", false)
        return reducers.scan(initialState) { state, reducer -> reducer.invoke(state) }
    }
}