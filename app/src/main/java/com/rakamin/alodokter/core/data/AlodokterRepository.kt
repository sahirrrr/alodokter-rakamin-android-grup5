package com.rakamin.alodokter.core.data

import com.rakamin.alodokter.core.data.source.local.LocalDataSource
import com.rakamin.alodokter.core.data.source.remote.RemoteDataSource

class AlodokterRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
}