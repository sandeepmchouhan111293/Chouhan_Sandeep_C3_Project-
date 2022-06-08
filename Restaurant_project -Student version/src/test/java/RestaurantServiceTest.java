import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantServiceTest {

	RestaurantService service = new RestaurantService();
	Restaurant restaurant;
	Restaurant restaurant1;
	LocalTime openingTime;
	LocalTime closingTime;

	// REFACTOR ALL THE REPEATED LINES OF CODE\
	@BeforeEach
	void setup() {
		openingTime = LocalTime.of(10, 00);
		closingTime = LocalTime.of(23, 00);
		restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
			throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE
		restaurant1 = service.addRestaurant("Cafe Coffe Day", "Bangalore", openingTime, closingTime);
		assertEquals(restaurant1, service.findRestaurantByName("Cafe Coffe Day"));

	}

	// You may watch the video by Muthukumaran on how to write exceptions in Course
	// 3: Testing and Version control: Optional content
	@Test
	public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE

		assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Pantry d'or"));
	}
	// <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
	// RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.removeRestaurant("Amelie's cafe");
		assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
	}

	@Test
	public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

		assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
	}

	@Test
	public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
	}
	// <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
	// RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
	
}