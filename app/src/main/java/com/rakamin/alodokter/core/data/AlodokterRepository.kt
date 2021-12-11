package com.rakamin.alodokter.core.data

import com.rakamin.alodokter.core.data.source.local.LocalDataSource
import com.rakamin.alodokter.core.data.source.remote.RemoteDataSource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.utils.DataMapper
import com.rakamin.alodokter.domain.model.LoginModel
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
                return localDataSource.getUserLogin().map { DataMapper.mapLoginEntitiesToDomain(it) }
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
}