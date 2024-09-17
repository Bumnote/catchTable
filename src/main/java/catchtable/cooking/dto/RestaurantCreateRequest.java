package catchtable.cooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
public class RestaurantCreateRequest {

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String menu;

}
