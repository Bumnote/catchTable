package catchtable.cooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
public class Restaurant {

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String menu;

}
