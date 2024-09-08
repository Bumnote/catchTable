package catchtable.cooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RestaurantDto {

    private String name;
    private String address;
    private String menu;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
