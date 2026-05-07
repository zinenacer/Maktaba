package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabase.postgrest["Book"]
            .select()
            .decodeList<Book>()
        emit(books)
    }.flowOn(Dispatchers.IO)

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return try {
            supabase.postgrest["Book"]
                .select {
                    filter {
                        eq("isbn", isbn)
                    }
                }.decodeSingleOrNull<Book>()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addBook(book: Book, imageBytes: ByteArray?) {
        var imageUrl = book.imageUrl
        
        if (imageBytes != null) {
            val fileName = "book_${book.isbn}.jpg"
            val bucket = supabase.storage["book_covers"]
            bucket.upload(fileName, imageBytes) {
                upsert = true
            }
            imageUrl = bucket.publicUrl(fileName)
        }
        
        val bookToSave = book.copy(imageUrl = imageUrl)
        supabase.postgrest["Book"].insert(bookToSave)
    }
}
