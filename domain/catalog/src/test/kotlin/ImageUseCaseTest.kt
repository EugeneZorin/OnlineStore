import com.example.catalog.repository.GetDataTransformerRepository
import com.example.catalog.usecase.ImageUseCase


suspend fun main() {
    val test = ImageUseCaseTest()
    test.setUp()
    test.getImage_should_return_data_on_success()
    test.getImage_should_throw_exception_on_error()
    test.getImage_should_return_empty_map_when_no_data()
    println("All tests passed")
}

class ImageUseCaseTest {

    private lateinit var imageUseCase: ImageUseCase
    private lateinit var mockRepository: MockGetDataTransformerRepository

    fun setUp() {
        mockRepository = MockGetDataTransformerRepository()
        imageUseCase = ImageUseCase(mockRepository)
    }

    suspend fun getImage_should_return_data_on_success() {
        mockRepository.shouldReturnError = false
        mockRepository.mockData = mutableMapOf("image1" to ByteArray(0))
        val result = imageUseCase.getImage()
        assert(result == mockRepository.mockData) { "Test failed: Expected ${mockRepository.mockData}, but got $result" }
        println("Test getImage_should_return_data_on_success passed")
    }

    suspend fun getImage_should_throw_exception_on_error() {
        mockRepository.shouldReturnError = true
        try {
            imageUseCase.getImage()
            assert(false) { "Test failed: Expected exception, but got success" }
        } catch (e: Exception) {
            assert(e.message == "Error") { "Test failed: Expected 'Error', but got '${e.message}'" }
            println("Test getImage_should_throw_exception_on_error passed")
        }
    }

    suspend fun getImage_should_return_empty_map_when_no_data() {
        mockRepository.shouldReturnError = false
        mockRepository.mockData = mutableMapOf()
        val result = imageUseCase.getImage()
        assert(result.isEmpty()) { "Test failed: Expected empty map, but got $result" }
        println("Test getImage_should_return_empty_map_when_no_data passed")
    }

    class MockGetDataTransformerRepository : GetDataTransformerRepository {
        var shouldReturnError = false
        var mockData = mutableMapOf<String, ByteArray>()

        override suspend fun dataTransformer(): MutableMap<String, ByteArray> {
            if (shouldReturnError) throw Exception("Error")
            return mockData
        }
    }
}
