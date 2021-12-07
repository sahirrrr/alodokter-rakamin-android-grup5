package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.domain.repository.IAlodokterRepository

class AlodokterInteractor(private val alodokterRepositoryImp: IAlodokterRepository): AlodokterUseCase {
}