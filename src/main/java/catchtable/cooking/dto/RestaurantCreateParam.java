package catchtable.cooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateParam {

    private String name;

    private String address;

    private String phoneNumber;

    private List<MenuCreateRequest> menus;

    public RestaurantCreateParam of(RestaurantCreateRequest request) {
        return RestaurantCreateParam.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .menus(request.getMenus())
                .build();
    }

}
