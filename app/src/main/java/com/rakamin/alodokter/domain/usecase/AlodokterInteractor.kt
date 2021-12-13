package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.ArticleModel
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

    override fun getProfile(idUser: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.getProfile(idUser)   
    }
    
    override fun getArticle(): Flowable<Resource<List<ArticleModel>>> {
        return alodokterRepositoryImp.getArticle()
    }
    
    override fun postRegister(name: String, email: String, password: String, passwordConfirmation: String): Flowable<Resource<List<RegisterModel>>> {
        return alodokterRepositoryImp.postRegister(name, email, password, passwordConfirmation)
    }

    override fun userLogout() {
        alodokterRepositoryImp.userLogout()
    }
}