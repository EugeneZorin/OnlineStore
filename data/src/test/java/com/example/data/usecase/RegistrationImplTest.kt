/*
package com.example.data.usecase

import com.example.data.room.User
import com.example.data.room.UserDao
import com.example.registration.entities.SavingDataEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RegistrationImplTest {

    @Mock
    lateinit var userDao: UserDao

    private lateinit var registrationImpl: RegistrationImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        registrationImpl = RegistrationImpl(userDao)

    }


    @Test
    fun `test search number phone in database`() = runBlocking {
        val result = registrationImpl.searchNumber("11111111111")
        assertEquals(true, result)
    }

    @Test
    fun `test insert`() = runBlocking {
        val savingDataEntity = SavingDataEntity("John", "Doe", "11111113311")
        val user = User(name = savingDataEntity.name, surname = savingDataEntity.surname, numberPhone = savingDataEntity.numberPhone)
        registrationImpl.insert(savingDataEntity)
        verify(userDao).insert(user)
    }

    @Test
    fun `test searchNumber`() = runBlocking {
        val numberPhone = "1111111111"
        `when`(userDao.getNumber(numberPhone)).thenReturn(true)
        val result = registrationImpl.searchNumber(numberPhone)
        assertEquals(true, result)
    }

    @Test
    fun `test delete`() = runBlocking {
        val id = "111"
        registrationImpl.delete(id)
        verify(userDao).delete(id)
    }

}*/
