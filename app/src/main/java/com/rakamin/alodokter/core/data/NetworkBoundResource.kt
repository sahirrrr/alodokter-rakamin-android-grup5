package com.rakamin.alodokter.core.data

import android.annotation.SuppressLint
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = PublishSubject.create<Resource<ResultType>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()
        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe {
                    value ->
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFetch(value)){
                    fetchFromNetwork()
                } else{
                    result.onNext(Resource.Success(value))
                }
            }
        compositeDisposable.add(db)
    }

    private fun fetchFromNetwork() {

        val apiResponse = createCall()

        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete{
                compositeDisposable.dispose()
            }
            .subscribe { response ->
                when(response){
                    is ApiResponse.Success -> {
                        saveCallResult(response.data)
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Success(it))
                            }
                    }
                    is ApiResponse.Empty -> {
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Success(it))
                            }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }
            }
        compositeDisposable.add(response)
    }

    protected abstract fun loadFromDB(): Flowable<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract fun saveCallResult(data: RequestType)

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    private fun onFetchFailed() {}

    fun asFlowAble() : Flowable<Resource<ResultType>> = result.toFlowable(BackpressureStrategy.BUFFER)
}