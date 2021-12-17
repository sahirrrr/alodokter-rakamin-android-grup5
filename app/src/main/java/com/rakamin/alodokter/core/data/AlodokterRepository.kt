package com.rakamin.alodokter.core.data

import com.rakamin.alodokter.core.data.source.local.LocalDataSource
import com.rakamin.alodokter.core.data.source.remote.RemoteDataSource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.*
import com.rakamin.alodokter.core.utils.DataMapper
import com.rakamin.alodokter.domain.model.*
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlodokterRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAlodokterRepository {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<UserModel>>> =
        object : NetworkBoundResource<List<UserModel>, LoginResponse>() {
            override fun loadFromDB(): Flowable<List<UserModel>> {
                return localDataSource.getUserData().map { DataMapper.mapUserEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<UserModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: LoginResponse) {
                val userLogin = DataMapper.mapLoginResponseToEntities(data)
                localDataSource.insertUserData(userLogin)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<LoginResponse>> {
                return remoteDataSource.postUserLogin(email, password)
            }
        }.asFlowAble()

    override fun putUserProfile(idUser: String, noHp: String, tglLahir: String, kotaKab: String
    ): Flowable<Resource<List<UserModel>>> = object : NetworkBoundResource<List<UserModel>, EditProfileResponse>() {
        override fun loadFromDB(): Flowable<List<UserModel>> {
            return localDataSource.getUserData().map { DataMapper.mapUserEntitiesToDomain(it) }
        }

        override fun shouldFetch(data: List<UserModel>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun saveCallResult(data: EditProfileResponse) {
            val editProfile = DataMapper.mapEditProfileResponseToEntities(data)
            localDataSource.editUserProfile(editProfile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

        override fun createCall(): Flowable<ApiResponse<EditProfileResponse>> {
            return remoteDataSource.putUserProfile(idUser, noHp, tglLahir, kotaKab)
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

    override fun postForgotPassword(email: String): Flowable<ApiResponse<ForgotPasswordResponse>>  {
        return remoteDataSource.postForgotPassword(email)
    }

    override fun getUserData(): Flowable<List<UserModel>> {
        return localDataSource.getUserData().map { DataMapper.mapUserEntitiesToDomain(it) }
    }

    override fun getProfile(idUser: String): Flowable<Resource<List<UserModel>>> =
        object : NetworkBoundResource<List<UserModel>, ProfileResponse>() {
            override fun loadFromDB(): Flowable<List<UserModel>> {
                return localDataSource.getUserData().map { DataMapper.mapUserEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<UserModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: ProfileResponse) {
                val userProfile = DataMapper.mapProfileResponseToEntities(data)
                localDataSource.insertUserData(userProfile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<ProfileResponse>> {
                return remoteDataSource.getUserProfile(idUser)
            }
        }.asFlowAble()
           
    override fun getArticle(): Flowable<Resource<List<ArticleModel>>> =
        object : NetworkBoundResource<List<ArticleModel>, ArticleResponse>() {
            override fun loadFromDB(): Flowable<List<ArticleModel>> {
                return localDataSource.getArticles().map { DataMapper.mapArticleEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<ArticleModel>?): Boolean {
                return true
            }

            override fun saveCallResult(data: ArticleResponse) {
                val article = DataMapper.mapArticleResponseToArticleEntities(data)
                localDataSource.insertArticle(article).subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<ArticleResponse>> {
                return remoteDataSource.getArticle()
            }
        }.asFlowAble()

    override fun getDoctor(): Flowable<Resource<List<ListDoctorModel>>> =
        object : NetworkBoundResource<List<ListDoctorModel>, ListDoctorResponse>() {
            override fun loadFromDB(): Flowable<List<ListDoctorModel>> {
                return localDataSource.getDoctor().map { DataMapper.mapDoctorEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<ListDoctorModel>?): Boolean {
                return true
            }

            override fun saveCallResult(data: ListDoctorResponse) {
                val doctor = DataMapper.mapDoctorResponseToDoctorEntities(data)
                localDataSource.insertDoctor(doctor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<ListDoctorResponse>> {
                return remoteDataSource.getDataDoctor()
            }
        }.asFlowAble()

    override fun getDoctorDetail(idDoctor: String): Flowable<Resource<List<DetailDoctorModel>>> =
        object : NetworkBoundResource<List<DetailDoctorModel>, DetailDoctorResponse>() {
            override fun loadFromDB(): Flowable<List<DetailDoctorModel>> {
                return localDataSource.getDoctorDetail(idDoctor).map { DataMapper.mapDetailDoctorEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<DetailDoctorModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: DetailDoctorResponse) {
                val doctorProfile = DataMapper.mapDetailDoctorResponseToEntities(data)
                localDataSource.insertDoctorDetail(doctorProfile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<DetailDoctorResponse>> {
                return remoteDataSource.getDoctorDetail(idDoctor)
            }
        }.asFlowAble()

    override fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorResponse>>> {
        return remoteDataSource.searchDoctor(query)
    }

    override fun userLogout() {
        localDataSource.userLogout()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}