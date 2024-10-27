package catchtable.cooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantCreateParam {

    private String name;

    private String phoneNumber;

    private String address;

    private String menu;

    public RestaurantCreateParam(RestaurantCreateRequest request) {
        this.name = request.getName();
        this.phoneNumber = request.getPhoneNumber();
        this.address = request.getAddress();
        this.menu = request.getMenu();
    }

}
