package catchtable.cooking.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantCreateParam {

    private String name;

    private String address;

    private String phoneNumber;

    public RestaurantCreateParam(RestaurantCreateRequest request) {
        this.name = request.getName();
        this.phoneNumber = request.getPhoneNumber();
        this.address = request.getAddress();
    }

}
