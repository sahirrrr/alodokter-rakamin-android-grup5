package com.rakamin.alodokter.domain.usecase

import androidx.room.Query
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.DoctorSearchResponse
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.DoctorModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.model.UserModel
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable

class AlodokterInteractor(private val alodokterRepositoryImp: IAlodokterRepository): AlodokterUseCase {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.postLogin(email, password)
    }

    override fun getUserData(): Flowable<List<UserModel>> {
        return alodokterRepositoryImp.getUserData()
    }

    override fun postRegister(name: String, email: String, password: String, passwordConfirmation: String): Flowable<Resource<List<RegisterModel>>> {
        return alodokterRepositoryImp.postRegister(name, email, password, passwordConfirmation)
    }

    override fun getProfile(idUser: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.getProfile(idUser)   
    }
    
    override fun getArticle(): Flowable<Resource<List<ArticleModel>>> {
        return alodokterRepositoryImp.getArticle()
    }

    override fun getDoctor(): Flowable<Resource<List<DoctorModel>>>{
        return alodokterRepositoryImp.getDoctor()
    }

    override fun getDoctorById(id: Int): Flowable<Resource<List<DoctorModel>>>{
        return alodokterRepositoryImp.getDoctorById(id)
    }

    override fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorSearchResponse>>>{
        return alodokterRepositoryImp.searchDoctor(query)
    }

    override fun userLogout() {
        alodokterRepositoryImp.userLogout()
    }
}