package com.jeffrey.functionalapp

data class Lce<out T>(
        val isLoading: Boolean,
        val error: Throwable?,
        val data: T?
) {
    fun hasError() = error != null

    companion object {
        fun <T> data(data: T): Lce<T> {
            return Lce(false, null, data)
        }

        fun <T> error(error: Throwable): Lce<T> {
            return Lce(false, error, null)
        }

        fun <T> loading(): Lce<T> {
            return Lce(true, null, null)
        }
    }
}
