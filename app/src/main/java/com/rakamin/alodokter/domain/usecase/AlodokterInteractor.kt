package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.response.DoctorResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.domain.model.*
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.ArticleSearchResponse
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable

class AlodokterInteractor(private val alodokterRepositoryImp: IAlodokterRepository): AlodokterUseCase {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.postLogin(email, password)
    }

    override fun getUserData(): Flowable<List<UserModel>> {
        return alodokterRepositoryImp.getUserData()
    }

    override fun putUserProfile(
        idUser: String,
        noHp: String,
        tglLahir: String,
        kotaKab: String
    ): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.putUserProfile(idUser, noHp, tglLahir, kotaKab)
    }

    override fun postRegister(name: String, email: String, password: String, passwordConfirmation: String): Flowable<Resource<List<RegisterModel>>> {
        return alodokterRepositoryImp.postRegister(name, email, password, passwordConfirmation)
    }

    override fun postForgotPassword(email: String): Flowable<ApiResponse<ForgotPasswordResponse>> {
        return alodokterRepositoryImp.postForgotPassword(email)
    }

    override fun getProfile(idUser: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.getProfile(idUser)   
    }
    
    override fun getArticle(): Flowable<Resource<List<ArticleModel>>> {
        return alodokterRepositoryImp.getArticle()
    }

    override fun articleSearch(query: String): Flowable<ApiResponse<List<ArticleSearchResponse>>> {
        return alodokterRepositoryImp.articleSearch(query)
    }

    override fun getArticleById(id: Int): Flowable<Resource<List<ArticleModel>>> {
        return alodokterRepositoryImp.getArticleById(id)
    }

    override fun getDoctor(): Flowable<Resource<List<ListDoctorModel>>> {
        return alodokterRepositoryImp.getDoctor()
    }

    override fun getDoctorDetail(idDoctor: String): Flowable<Resource<List<DetailDoctorModel>>> {
        return alodokterRepositoryImp.getDoctorDetail(idDoctor)
    }

    override fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorResponse>>> {
        return alodokterRepositoryImp.searchDoctor(query)
    }

    override fun userLogout() {
        alodokterRepositoryImp.userLogout()
    }
}