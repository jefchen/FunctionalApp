package com.jeffrey.functionalapp

import io.reactivex.Observable

interface CommentFormModelContract {
    fun createDataStream(
            textChangedEvents: Observable<TextChangedEvent>,
            submitCommentEvents: Observable<SubmitCommentEvent>
    ): Observable<CommentFormData>
}