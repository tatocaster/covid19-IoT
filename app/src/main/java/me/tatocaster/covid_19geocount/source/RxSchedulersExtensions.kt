package me.tatocaster.covid_19geocount.source

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tatocaster.covid_19geocount.CovidStatsApplication
import retrofit2.Response

// TODO: change later with correct abstractions and DI stuff

fun <T> Single<T>.applySchedulers(): Single<T> {
    return if (CovidStatsApplication.config.mockMode)
        subscribeOn(Schedulers.trampoline()).observeOn(AndroidSchedulers.mainThread())
    else
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applySchedulers(): Completable {
    return if (CovidStatsApplication.config.mockMode)
        subscribeOn(Schedulers.trampoline()).observeOn(AndroidSchedulers.mainThread())
    else
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.applySchedulers(): Maybe<T> {
    return if (CovidStatsApplication.config.mockMode)
        subscribeOn(Schedulers.trampoline()).observeOn(AndroidSchedulers.mainThread())
    else
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<Response<T>>.getResponseBody(): Single<T> {
    return map {
        it.body()!!
    }
}