package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantItemResponse {

    private String name;

    private String address;

    private String phoneNumber;

    private List<MenuItemResponse> menus = new ArrayList<>();

    private List<ReviewItemResponse> reviews = new ArrayList<>();

    private List<WaitingItemResponse> waitings = new ArrayList<>();

    private List<ReservationItemResponse> reservations = new ArrayList<>();

}
