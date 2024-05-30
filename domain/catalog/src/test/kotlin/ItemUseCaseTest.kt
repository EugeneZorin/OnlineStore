import com.example.catalog.entity.Feedback
import com.example.catalog.entity.Info
import com.example.catalog.entity.Item
import com.example.catalog.entity.Items
import com.example.catalog.entity.Price
import com.example.catalog.repository.GetDataRepository
import com.example.catalog.usecase.ItemUseCase

suspend fun main(){
    val test = ItemUseCaseTest()
    test.setUp()
    test.getItem_should_return_data_on_success()
    test.getItem_should_throw_exception_on_error()
    println("All tests passed")
}
class ItemUseCaseTest {

    private lateinit var itemUseCase: ItemUseCase
    private lateinit var getDataRepository: MockGetDataRepository


    fun setUp(){
        getDataRepository = MockGetDataRepository()
        itemUseCase = ItemUseCase(getDataRepository)
    }

    suspend fun getItem_should_return_data_on_success(){
        getDataRepository.shouldReturnError = false
        getDataRepository.item = Items(
            items = listOf(
                Item(
                    id = "0",
                    title = "0",
                    subtitle = "0",
                    price = Price(
                        price = "0",
                        discount = 0,
                        priceWithDiscount = "0",
                        unit = "0"
                    ),
                    feedback = Feedback(
                        count = 0,
                        rating = 0.0
                    ),
                    tags = listOf("tag1", "tag2"),
                    available = 0,
                    description = "0",
                    info = listOf(
                        Info(
                            title = "title1",
                            value = "value1"
                        ),
                        Info(
                            title = "title2",
                            value = "value2"
                        )
                    ),
                    ingredients = "0"
                )
            )
        )
        val result = itemUseCase.getDataUseCase()
        assert(result == getDataRepository.item) {
            "Test failed: Expected ${getDataRepository.item}, but got $result"
        }
        println("Test getItem_should_return_data_on_success passed")
    }

    suspend fun getItem_should_throw_exception_on_error(){
        getDataRepository.shouldReturnError = true
        try {
            itemUseCase.getDataUseCase()
            assert(false) { "Test failed: Expected exception, but got success" }
        } catch (e: Exception) {
            assert(e.message == "Error") { "Test failed: Expected 'Error', but got '${e.message}'" }
            println("Test getItem_should_throw_exception_on_error passed")
        }
    }

    class MockGetDataRepository : GetDataRepository {
        var shouldReturnError = false
        var item = Items(items = listOf())
        override suspend fun getData(): Items {
            if (shouldReturnError) throw Exception("Error")
            return item
        }

    }
}