package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> = flow {
        val categories = supabase.postgrest["Cat"]
            .select()
            .decodeList<Category>()
        emit(categories)
    }.flowOn(Dispatchers.IO)

    override suspend fun getCategoryById(id: Long): Category? {
        return try {
            supabase.postgrest["Cat"]
                .select {
                    filter { eq("id", id) }
                }.decodeSingleOrNull<Category>()
        } catch (e: Exception) {
            null
        }
    }
}
