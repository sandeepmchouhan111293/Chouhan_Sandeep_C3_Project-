import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RestaurantTest {
	Restaurant restaurant;
	// REFACTOR ALL THE REPEATED LINES OF CODE

	@BeforeEach
	void setup() {
		LocalTime openingTime = LocalTime.parse("10:30:00");
		LocalTime closingTime = LocalTime.parse("22:00:00");
		restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 120);
		restaurant.addToMenu("Vegetable lasagne", 250);
		restaurant.addToMenu("Latte", 150);
		restaurant.addToMenu("Mocha", 300);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	// -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN
	// INTO ANY TROUBLE
	@Test
	public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
		// WRITE UNIT TEST CASE HERE

		Restaurant restaurantSpy = Mockito.spy(restaurant);

		doReturn(LocalTime.of(18, 00)).when(restaurantSpy).getCurrentTime();

		boolean actual = restaurantSpy.isRestaurantOpen();

		assertTrue(actual);

	}

	@Test
	public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
		// WRITE UNIT TEST CASE HERE

		Restaurant restaurantSpy = Mockito.spy(restaurant);

		doReturn(LocalTime.of(6, 00)).when(restaurantSpy).getCurrentTime();

		boolean actual = restaurantSpy.isRestaurantOpen();

		assertEquals(false, actual);
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void adding_item_to_menu_should_increase_menu_size_by_1() {

		int initialMenuSize = restaurant.getMenu().size();
		restaurant.addToMenu("Sizzling brownie", 319);
		assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("Vegetable lasagne");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_that_does_not_exist_should_throw_exception() {

		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
	}
	// <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Test
	public void total_estimate_should_return_right_amount_when_items_are_selected() {

		List<String> itemNames = Arrays.asList("Latte", "Mocha");

		double expected = 450.0;

		assertEquals(expected, restaurant.getTotalEstimate(itemNames));

	}

	@Test
	public void total_estimate_should_return_zero_when_no_items_are_selected() {

		List<String> itemNames = new ArrayList<>();

		double expected = 0.0;

		assertEquals(expected, restaurant.getTotalEstimate(itemNames));

	}
	
}