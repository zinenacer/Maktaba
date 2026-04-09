package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * Repository for managing book data
 * This follows the Repository pattern to abstract data sources
 */
class BookRepository {

    /**
     * TODO for Students (TP1 - Exercise 1):
     * Complete the book information for each book in the list below.
     * Add the following information for each book:
     * - isbn: Use a valid ISBN-13 format (e.g., "978-3-16-148410-0")
     * - nbPages: Add the actual number of pages
     *
     * Example:
     * Book(
     *     isbn = "978-0-13-468599-1",
     *     title = "Clean Code",
     *     nbPages = 464
     * )
     */
    private val booksList = listOf(
        Book(isbn = "9780135398524", title = "Clean Code", nbPages = 464  ),
        Book(isbn = "9780132119177", title = "The Pragmatic Programmer", nbPages = 352 ),
        Book(isbn = "9780201485370", title = "Design Patterns", nbPages = 416 ),
        Book(isbn = "9780133065268", title = "Refactoring", nbPages = 448 ),
        Book(isbn = "9780596007126", title = "Head First Design Patterns", nbPages = 638),
        Book(isbn = "9780132350884", title = "Clean Architecture", nbPages = 432 ),
        Book(isbn = "9780134494166", title = "The Clean Coder", nbPages = 256 ),
        Book(isbn = "9780134757599", title = "Working Effectively with Legacy Code", nbPages = 400 ),
        Book(isbn = "9780321127426", title = "Patterns of Enterprise Application Architecture", nbPages = 560 ),
        Book(isbn = "9780137081073", title = "The Art of Unit Testing", nbPages = 320 )c
    )

    /**
     * TODO for Students (TP1 - Exercise 2):
     * Add 5 more books to the list above.
     * Choose books related to Computer Science, Programming, or any topic you like.
     * Remember to include complete information (ISBN, title, nbPages).
     *
     * Tip: You can find ISBN numbers for books on:
     * - Google Books
     * - Amazon
     * - GoodReads
     */

    /**
     * Get all books from the repository
     * @return List of all books
     */
    fun getAllBooks(): List<Book> {
        return booksList
    }

    /**
     * Get a book by ISBN
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}
