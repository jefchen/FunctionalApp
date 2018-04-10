package com.jeffrey.functionalapp

import android.util.Log
import io.reactivex.Observable
import java.util.*
import kotlin.concurrent.schedule

/**
 * Wish
 * Created by Jeffrey Chen on 4/9/18.
 * Copyright (c) 2017 ContextLogic. All rights reserved.
 */

class CommentService {
    fun submit(comment: String): Observable<Lce<String>> {
        Log.i("CommentService", "Comment submitted: \n$comment")
        return Observable.create<Lce<String>> { emitter ->
            emitter.onNext(Lce(true, null, null))

            val timer = Timer()
            timer.schedule(1000) {
                emitter.onNext(Lce(false, null, comment))
                emitter.onComplete()
            }
        }
    }
}