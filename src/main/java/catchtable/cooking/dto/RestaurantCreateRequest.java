package catchtable.cooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantCreateRequest {

    private String name;

    private String address;

    private String phoneNumber;

}
