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
public class RestaurantItemAllResponse {

    private String name;

    private String address;

    private String phoneNumber;

    @Builder.Default
    private List<MenuItemResponse> menus = new ArrayList<>();

    @Builder.Default
    private List<ReviewItemResponse> reviews = new ArrayList<>();

    @Builder.Default
    private List<WaitingItemResponse> waitings = new ArrayList<>();

    @Builder.Default
    private List<ReservationItemResponse> reservations = new ArrayList<>();

}
