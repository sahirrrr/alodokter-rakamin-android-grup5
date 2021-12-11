package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.UserModel
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable

class AlodokterInteractor(private val alodokterRepositoryImp: IAlodokterRepository): AlodokterUseCase {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.postLogin(email, password)
    }

    override fun getProfile(idUser: String): Flowable<Resource<List<UserModel>>> {
        return alodokterRepositoryImp.getProfile(idUser)
    }
}