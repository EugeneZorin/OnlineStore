package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.room.User
import com.example.data.room.UserDao
import com.example.data.room.UserDatabase
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
    fun sdfasdf() = runBlocking {
        val user = User(name = "User", surname = "Surname", numberPhone = "1111111111")
        userDatabase.userDao().insert(user)
        val result = userDatabase.userDao().getNumber("1111311111")
        assertEquals(false, result)
    }


    @After
    @Throws(IOException::class)
    fun tearDown(){
        userDatabase.close()
    }

}
