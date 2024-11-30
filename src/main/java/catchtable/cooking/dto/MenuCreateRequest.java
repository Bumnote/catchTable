package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Menu;
import catchtable.cooking.persist.domain.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuCreateRequest {

    private String name;

    private Integer price;

    private String description;

    public Menu toEntity(MenuCreateRequest request, Restaurant restaurant) {
        return Menu.builder()
                .restaurant(restaurant)
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
    }

}
