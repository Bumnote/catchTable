package catchtable.cooking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RestaurantCreateRequest {

    private String name;

    private String address;

    private String phoneNumber;

    private List<MenuCreateRequest> menus;

}
