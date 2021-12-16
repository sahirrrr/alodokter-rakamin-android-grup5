package com.rakamin.alodokter.core.data.source.remote

import android.annotation.SuppressLint
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.network.ApiService
import com.rakamin.alodokter.core.data.source.remote.response.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiService: ApiService) {

    fun postUserLogin(email: String, password: String): Flowable<ApiResponse<LoginResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<LoginResponse>>()
        val client = apiService.postLogin(email, password)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                responseResult.onNext(ApiResponse.Success(response))
            }, { error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUserProfile(idUser : String) : Flowable<ApiResponse<ProfileResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<ProfileResponse>>()
        val client = apiService.showProfile(idUser)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                responseResult.onNext(ApiResponse.Success(response))
            }, { error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun postUserRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<ApiResponse<RegisterResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<RegisterResponse>>()
        val client = apiService.postRegister(name, email, password, passwordConfirmation)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                responseResult.onNext(ApiResponse.Success(response))
            }, { error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }
    
    fun getArticle(): Flowable<ApiResponse<ArticleResponse>> {
        val responseBody = PublishSubject.create<ApiResponse<ArticleResponse>>()
        val client = apiService.getArticles()
        client.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                responseBody.onNext(ApiResponse.Success(it))
            },{
            responseBody.onNext(ApiResponse.Error(it.message.toString()))
        })
        return responseBody.toFlowable(BackpressureStrategy.BUFFER)

    }

    fun getDataDoctor(): Flowable<ApiResponse<DoctorResponse>>{
        val responseBody = PublishSubject.create<ApiResponse<DoctorResponse>>()
        val client = apiService.getDoctor()
        client.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                responseBody.onNext(ApiResponse.Success(it))
            },{
                responseBody.onNext(ApiResponse.Error(it.message.toString()))
            })
        return responseBody.toFlowable((BackpressureStrategy.BUFFER))
    }

    fun getDoctorById(id: Int) : Flowable<ApiResponse<DoctorResponse>>{
        val responseBody = PublishSubject.create<ApiResponse<DoctorResponse>>()
        val client = apiService.getDocterById(id)
        client.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                responseBody.onNext((ApiResponse.Success(it)))
            },{
                responseBody.onNext(ApiResponse.Error(it.message.toString()))
            })
        return responseBody.toFlowable((BackpressureStrategy.BUFFER))
    }

    fun searchDoctor(query : String): Flowable<ApiResponse<List<DoctorSearchResponse>>>{
        val responseBody = PublishSubject.create<ApiResponse<List<DoctorSearchResponse>>>()
        val client = apiService.searchDoctor(query)
        client.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                responseBody.onNext(ApiResponse.Success(it))
            },{
                responseBody.onNext(ApiResponse.Error(it.message.toString()))
            })
        return responseBody.toFlowable(BackpressureStrategy.BUFFER)
    }
}