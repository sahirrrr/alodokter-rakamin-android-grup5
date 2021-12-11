package com.rakamin.alodokter.core.data

import com.rakamin.alodokter.core.data.source.local.LocalDataSource
import com.rakamin.alodokter.core.data.source.remote.RemoteDataSource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.ArticleResponse
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.RegisterResponse
import com.rakamin.alodokter.core.utils.DataMapper
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlodokterRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAlodokterRepository {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<LoginModel>>> =
        object : NetworkBoundResource<List<LoginModel>, LoginResponse>() {
            override fun loadFromDB(): Flowable<List<LoginModel>> {
                return localDataSource.getUserLogin()
                    .map { DataMapper.mapLoginEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<LoginModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: LoginResponse) {
                val userLogin = DataMapper.mapLoginResponseToEntities(data)
                localDataSource.insertUserLogin(userLogin)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<LoginResponse>> {
                return remoteDataSource.postUserLogin(email, password)
            }
        }.asFlowAble()

    override fun getArticle(): Flowable<Resource<List<ArticleModel>>> =
        object : NetworkBoundResource<List<ArticleModel>, ArticleResponse>() {
            override fun loadFromDB(): Flowable<List<ArticleModel>> {
                return localDataSource.getArticles()
                    .map { DataMapper.mapArticleEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<ArticleModel>?): Boolean {
                return data == null || data.isEmpty()

            }


            override fun saveCallResult(data: ArticleResponse) {
                val article = DataMapper.mapArticleResponseToArticleEntities(data)
                localDataSource.insertArticle(article).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

            }

            override fun createCall(): Flowable<ApiResponse<ArticleResponse>> {
                return remoteDataSource.getArticle()
            }

        }.asFlowAble()

    override fun postRegister(name: String, email: String, password: String, passwordConfirmation: String): Flowable<Resource<List<RegisterModel>>> =
        object : NetworkBoundResource<List<RegisterModel>, RegisterResponse>() {
            override fun loadFromDB(): Flowable<List<RegisterModel>> {
                return localDataSource.getUserRegister().map { DataMapper.mapRegisterEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<RegisterModel>?): Boolean {
                return true
            }

            override fun saveCallResult(data: RegisterResponse) {
                val userRegister = DataMapper.mapRegisterResponseToEntities(data)
                localDataSource.insertUserRegister(userRegister)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<RegisterResponse>> {
                return remoteDataSource.postUserRegister(name, email, password, passwordConfirmation)
            }
        }.asFlowAble()
}