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
public class RestaurantItemDetailResponse {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private List<MenuItemResponse> menus = new ArrayList<>();

}
