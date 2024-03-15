package com.example.data.usecase

import com.example.registration.entities.SavingDataEntity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RegistrationImplTest {

    @Test
    fun test() = runTest {
        val testRegistrationImpl = mockk<RegistrationImpl>(relaxed = true)
        var result = false
        testRegistrationImpl.insert(savingDataEntity = SavingDataEntity(
            name = "1",
            surname = "2",
            numberPhone = "3"
        ))

        result = testRegistrationImpl.searchNumber("3")

        Assert.assertEquals(true, result)
    }


}