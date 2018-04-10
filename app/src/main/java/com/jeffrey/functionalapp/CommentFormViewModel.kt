package com.jeffrey.functionalapp

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Wish
 * Created by Jeffrey Chen on 4/9/18.
 * Copyright (c) 2017 ContextLogic. All rights reserved.
 */

class CommentFormViewModel(private val model: CommentFormModel) {

    // Let's create a view state from the data provided.
    private fun createViewState(
            data: CommentFormData,
            onTextChanged: (TextChangedEvent) -> Unit,
            onSubmitClicked: (SubmitCommentEvent) -> Unit
    ): CommentFormViewState {

        val isSubmitting = data.submitRequest?.isLoading ?: false

        return CommentFormViewState(
                textEntered = data.comment,
                isSubmitButtonEnabled = data.isCommentValid && !isSubmitting,
                onTextEntered = { text ->
                    onTextChanged(TextChangedEvent(text))
                },
                onSubmitClicked = {
                    // We assume that view will disable submit button when
                    // isSubmitButtonEnabled = false
                    onSubmitClicked(SubmitCommentEvent(data.comment))
                }
        )
    }

    fun viewStateStream(): Observable<CommentFormViewState> {
        // define the relays that will allow to complete unidirectional data flow
        val textChangedEventRelay = PublishRelay.create<TextChangedEvent>()
        val submitEventRelay = PublishRelay.create<SubmitCommentEvent>()

        return model
                .createDataStream(
                        textChangedEvents = textChangedEventRelay,
                        submitCommentEvents = submitEventRelay
                )
                .map { data ->
                    // creating view state
                    createViewState(
                            data = data,
                            onTextChanged = { event ->
                                // Pass the event to the relay
                                textChangedEventRelay.accept(event)
                            },
                            onSubmitClicked = { event ->
                                // Pass the event to the relay
                                submitEventRelay.accept(event)
                            })
                }
    }

}