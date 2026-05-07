package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabase.postgrest["Book"].select().decodeList<Book>()
        emit(books)
    }

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return supabase.postgrest["Book"].select {
            filter {
                eq("isbn", isbn)
            }
        }.decodeSingleOrNull<Book>()
    }

    override suspend fun addBook(book: Book, imageBytes: ByteArray?) {
        var imageUrl = book.imageUrl
        
        if (imageBytes != null) {
            val fileName = "${book.isbn}.jpg"
            val bucket = supabase.storage["books_pics"]
            bucket.upload(fileName, imageBytes) {
                upsert = true
            }
            imageUrl = bucket.publicUrl(fileName)
        }
        
        val bookToSave = book.copy(imageUrl = imageUrl)
        supabase.postgrest["Book"].insert(bookToSave)
    }
}
