package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.accounts.room.User
import com.example.data.accounts.room.UserDao
import com.example.data.accounts.room.UserDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class UserTest {

    private lateinit var userDatabase: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp(){

        val context = ApplicationProvider.getApplicationContext<Context>()

        userDatabase = Room.inMemoryDatabaseBuilder(
            context,
            UserDatabase::class.java
        ).build()
        userDao = userDatabase.userDao()

    }

    @Test
    @Throws(Exception::class)
    fun checkingPhoneNumberDatabase() = runBlocking {

        val user = User(name = "User", surname = "Surname", numberPhone = "0000000000")
        userDatabase.userDao().insert(user)

        val result = userDatabase.userDao().getNumber("0000000000")

        assertEquals(true, result)
    }

    @Test
    fun databaseDeletionTest() = runBlocking {
        val user = User(name = "User", surname = "Surname", numberPhone = "0000000000")
        userDatabase.userDao().insert(user)

        userDatabase.userDao().delete("0000000000")
        val result = userDatabase.userDao().getNumber("0000000000")

        assertEquals(false, result)
    }

    @Test
    fun databaseInsert() = runBlocking {
        val user = User(name = "User", surname = "Surname", numberPhone = "0000000000")
        userDatabase.userDao().insert(user)

        val name = userDatabase.userDao().getUser("0000000000").name
        val surname = userDatabase.userDao().getUser("0000000000").surname
        val number = userDatabase.userDao().getUser("0000000000").numberPhone

        assertEquals("User", name)
        assertEquals("Surname", surname)
        assertEquals("0000000000", number)
    }


    @After
    @Throws(IOException::class)
    fun tearDown(){
        userDatabase.close()
    }

}
