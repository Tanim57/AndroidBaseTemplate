
package com.tanim.androidbasetemplate.data.mapper

import Resource
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getPackages(user: Int): Resource<ResourceString, ResourceString>
}